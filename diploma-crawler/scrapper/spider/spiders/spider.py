from urllib.parse import urlparse, urljoin
from bs4 import BeautifulSoup
from scrapy import signals
from domain.Domain import WebPage, WebPortal, WebPageBacklog

import json
import logging
import uuid
import requests
import os
import scrapy


class Spider(scrapy.Spider):
    name = 'diploma-crawler'
    start_urls = []
    web_portal: WebPortal

    def __init__(self, name=None, **kwargs):
        super().__init__(name=name, **kwargs)

    # def start_requests(self):
    #     for url in self.start_urls:
    #         yield scrapy.Request(url, self.parse, errback=self.error_callback)

    def parse(self, response):
        parsed_url = urlparse(response.url)

        # Парсим HTML страницу, для того, чтобы вытащить все имеющиеся на странице ссылки
        soup = BeautifulSoup(response.text, "lxml")

        web_page = WebPage()
        web_page.url = response.url
        web_page.page_text = response.text
        web_page.resource_name = parsed_url.path
        web_page.meta_data = json.dumps(self._join_tags(soup.find_all("meta")))
        web_page.head = json.dumps(self._join_tags(soup.find_all("head")))
        web_page.web_portal = self.web_portal

        web_page.save()
        # сообщаем,что страницу можно предобработать
        self._send_proprocess_event(web_page._DomainObject__id)

        # находим все ссылки на странице
        urls = soup.find_all('a', href=True)
        for url in urls:
            yield scrapy.Request(urljoin(response.url, url["href"]), self.parse)
            # todo: add with backlog
            # yield scrapy.Request(urljoin(response.url, url["href"]), self.parse, errback=self.error_callback, cb_kwargs={
            #     "web_page": web_page,
            #     "web_portal": self.web_portal
            # })

    def _send_proprocess_event(self, web_page_id: str) -> None:
        crawler_preprocessor_host = os.getenv("PREPROCESSOR_HOST")
        crawler_preprocessor_port = os.getenv("PREPROCESSOR_PORT")
        target_url = f'http://{crawler_preprocessor_host}:{crawler_preprocessor_port}/register_preprocess_task'
        data = {
            "web_page_id": str(web_page_id)
        }
        requests.post(target_url, json=data)

    def _join_tags(self, tags_list):
        result = dict()
        i = 0
        for tag in tags_list:
            tag_as_string = str(tag)
            result[i] = tag_as_string
            i += 1
        return result

    def error_callback(self, failure):
        # todo: add backlog
        # self.logger.warning('Get exception')
        # self.logger.exception(failure)
        # web_page_backlog = WebPageBacklog()
        # web_page_backlog.web_page_backlog
        pass
