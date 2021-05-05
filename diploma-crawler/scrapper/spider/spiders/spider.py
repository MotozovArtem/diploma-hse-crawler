import json
import logging
import uuid
from urllib.parse import urlparse, urljoin

import scrapy
from bs4 import BeautifulSoup
from scrapy import signals
from domain.Domain import WebPage, WebPortal


class Spider(scrapy.Spider):
    name = 'diploma-crawler'
    start_urls = []
    web_portal: WebPortal

    def __init__(self, name=None, **kwargs):
        super().__init__(name=name, **kwargs)
        self.__LOG: logging.Logger = logging.getLogger(__name__)

    def parse(self, response):
        parsed_url = urlparse(response.url)

        # Парсим HTML страницу, для того, чтобы вытащить все имеющиеся на странице ссылки
        soup = BeautifulSoup(response.text, "lxml")

        web_page = WebPage()
        web_page.url = response.url
        web_page.page_text = response.text
        web_page.meta_data = json.dumps(self._join_tags(soup.find_all("meta")))
        web_page.head = json.dumps(self._join_tags(soup.find_all("head")))
        web_page.web_portal = self.web_portal

        web_page.save()
        # находим все ссылки на странице
        urls = soup.find_all('a', href=True)
        for url in urls:
            yield scrapy.Request(urljoin(response.url, url["href"]), self.parse)

    def _join_tags(self, tags_list):
        result = dict()
        i = 0
        for tag in tags_list:
            tag_as_string = str(tag)
            result[i] = tag_as_string
            i += 1
        return result
