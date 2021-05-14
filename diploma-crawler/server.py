from domain.Domain import WebPage, WebPortal
from domain.Database import Database
from scrapy import signals
from scrapy.signalmanager import dispatcher
from scrapy.crawler import CrawlerRunner
from scrapper.spider.spiders.spider import Spider
from scrapper.YandexSearcher import YandexSearcher
from scrapper.constants import USER_AGENT
from flask import Flask
from flask import request
from flask import Response
from flask.logging import default_handler
from flask_cors import CORS
from urllib.parse import urlparse
from bs4 import BeautifulSoup
import logging
import requests
import os
import json

import crochet
crochet.setup()


# Создание объекта приложения-сервера
app = Flask(__name__)
cors = CORS(app)

DB = Database()

if os.getenv("PREPROCESSOR_HOST") is None:
    raise RuntimeError('PREPROCESSOR_HOST cannot be None')

# Создание объекта-стартера для сборщиков данных
crawl_runner = CrawlerRunner()

LOG_FORMATTER = logging.Formatter(
    "%(asctime)s %(name)s [%(threadName)s]: %(levelname)s %(message)s")

FILE_LOGGING_HANDLER = logging.FileHandler("trace.log")
FILE_LOGGING_HANDLER.setLevel(logging.DEBUG)
FILE_LOGGING_HANDLER.setFormatter(LOG_FORMATTER)
default_handler.setLevel(logging.INFO)
app.logger.addHandler(FILE_LOGGING_HANDLER)


@app.route("/start_crawl", methods=["POST"])
def start_crawl():
    if request.is_json:
        request_parameters = request.get_json(cache=False)
        target_url = request_parameters["target"]
        parsed_url = urlparse(target_url)
        app.logger.info("Receive request with - %s", target_url)

        try:
            saved_portal = WebPortal.objects.get(
                domain_name=parsed_url.hostname)
        except:
            saved_portal = None

        if saved_portal is None:
            response = requests.get(request_parameters["target"])
            soup = BeautifulSoup(response.text, "lxml")
            web_portal = WebPortal()
            web_portal.domain_name = parsed_url.hostname
            web_portal.portal_name = soup.find("title").text
            web_portal.save()
            app.logger.info("Saved WebPortal (id:%s)",
                            web_portal._DomainObject__id)
            crawl_target_url(target_url, web_portal)
        else:
            app.logger.info("Loaded WebPortal from database (id: %s)",
                            saved_portal._DomainObject__id)
            crawl_target_url(target_url, saved_portal)
        return Response(status=200)
    else:
        app.logger.info("Received non-json data")
        return Response(status=406)


def validate_search_sites_parameters(key_words, max_sites: int) -> bool:
    if key_words is None:
        return False
    if len(key_words) == 0:
        return False
    if max_sites > 10 or max_sites < 1:
        return False
    return True


@app.route("/search_sites", methods=["POST"])
def search_sites():
    if request.is_json:
        request_parameters = request.get_json(cache=False)
        key_words = request_parameters["key_words"]
        max_sites = request_parameters["max_sites"]

        if not validate_search_sites_parameters(key_words, max_sites):
            return Response(json.dumps(
                {
                    "error": "Parameters not valid",
                    "tip": "Key words cannot be empty or null. Max_sites should be in interval [1, 10]"
                }),
                status=406,
                content_type="application/json")
        searcher = YandexSearcher()
        searcher.set_headers({"User-Agent": USER_AGENT[0]})
        result_list = searcher.search(key_words)
        web_portals = []
        for url in result_list:
            parsed_url = urlparse(url)
            try:
                saved_portal = WebPortal.objects.get(
                    domain_name=parsed_url.hostname)
            except:
                saved_portal = None

            if saved_portal is None:
                response = requests.get(url)
                soup = BeautifulSoup(response.text, "lxml")
                web_portal = WebPortal()
                web_portal.domain_name = parsed_url.hostname
                web_portal.portal_name = soup.find("title").text
                web_portal.save()
                saved_portal = web_portal
                app.logger.info("Saved WebPortal (id:%s)",
                                web_portal._DomainObject__id)
            else:
                app.logger.info("Loaded WebPortal from database (id: %s)",
                                saved_portal._DomainObject__id)
            web_portals.append(saved_portal)
        start_crawl_found_sites(result_list, web_portals)
        return Response(status=200)
    else:
        app.logger.info("Received non-json data")
        return Response(status=406)


@crochet.run_in_reactor
def crawl_target_url(target_url: str, web_portal: WebPortal):
    dispatcher.connect(_crawler_result, signal=signals.item_scraped)

    app.logger.info("Configuring Spider")
    Spider.start_urls = [target_url]
    Spider.web_portals = {
        urlparse(target_url).hostname: web_portal
    }
    Spider.allowed_domains = [urlparse(target_url).hostname]
    app.logger.info("Starting crawling WebPortal (id: %s)",
                    web_portal._DomainObject__id)
    crawl_runner.crawl(Spider)


@crochet.run_in_reactor
def start_crawl_found_sites(found_sites: list, web_portals: list):
    dispatcher.connect(_crawler_result, signal=signals.item_scraped)

    app.logger.info("Configuring Spider")
    Spider.start_urls = found_sites
    Spider.web_portals = {k.domain_name: v for k, v in zip(web_portals, web_portals)}
    Spider.allowed_domains = [urlparse(url).hostname for url in found_sites]
    app.logger.info(
        "Start crawling for found sites: \n >>>> [%s]", ','.join(found_sites))
    crawl_runner.crawl(Spider)


def _crawler_result(item, response, spider):
    app.logger.info("Scrapped url %s", item)
    pass
