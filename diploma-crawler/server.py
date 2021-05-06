from domain.Domain import WebPage, WebPortal
from domain.Database import Database
from scrapy import signals
from scrapy.signalmanager import dispatcher
from scrapy.crawler import CrawlerRunner
from scrapper.spider.spiders.spider import Spider
from flask import Flask
from flask import request
from flask import Response
from flask.logging import default_handler
import logging
import requests
from urllib.parse import urlparse
from bs4 import BeautifulSoup

import crochet
crochet.setup()


# Создание объекта приложения-сервера
app = Flask(__name__)

DB = Database()
app.teardown_appcontext

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

        response = requests.get(request_parameters["target"])
        soup = BeautifulSoup(response.text, "lxml")
        web_portal = WebPortal()
        web_portal.domain_name = parsed_url.hostname
        web_portal.portal_name = soup.find("title").text
        web_portal.save()
        app.logger.info("Saved WebPortal (id:%s)", web_portal._DomainObject__id)

        crawl_target_url(target_url, web_portal)
        return Response(status=200)
    else:
        app.logger.info("Received non-json data")
        return Response(status=406)

@app.route("/search_sites", methods=["POST"])
def search_sites():
    if request.is_json:
        # todo: add logic
        return Response(status=200)
    else:
        app.logger.info("Received non-json data")
        return Response(status=406)


@crochet.run_in_reactor
def crawl_target_url(target_url: str, web_portal: WebPortal):
    dispatcher.connect(_crawler_result, signal=signals.item_scraped)

    Spider.start_urls = [target_url]
    Spider.web_portal = web_portal
    Spider.allowed_domains = [urlparse(target_url).hostname]
    crawl_runner.crawl(Spider)


def _crawler_result(item, response, spider):
    app.logger.info("Scrapped url %s", item)
    pass