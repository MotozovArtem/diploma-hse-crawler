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
    web_portals: dict
    visited_urls: set = set()

    def __init__(self, name=None, **kwargs):
        super().__init__(name=name, **kwargs)

    def start_requests(self):
        return [scrapy.Request(url, self.parse, errback=self.error_callback, cb_kwargs={"web_portal": self.web_portals[urlparse(url).hostname]}) for url in self.start_urls]

    def parse(self, response, web_portal):
        parsed_url = urlparse(response.url)

        # Парсим HTML страницу, для того, чтобы вытащить все имеющиеся на странице ссылки
        soup = BeautifulSoup(response.text, "lxml")

        web_page = WebPage()
        web_page.url = response.url
        web_page.page_text = response.text
        web_page.resource_name = parsed_url.path
        web_page.meta_data = json.dumps(self._join_tags(soup.find_all("meta")))
        web_page.head = json.dumps(self._join_tags(soup.find_all("head")))
        web_page.web_portal = web_portal

        web_page.save()
        # сообщаем,что страницу можно предобработать
        self._send_preprocess_event(web_page._DomainObject__id)

        # находим все ссылки на странице
        urls = soup.find_all('a', href=True)
        for url in urls:
            joined_url = urljoin(response.url, url["href"])
            if self._check_mail_javascript_tel_refs(joined_url) or parsed_url.hostname not in self.web_portals.keys():
                continue
            if joined_url not in self.visited_urls:
                yield scrapy.Request(joined_url, self.parse,
                                     errback=self.error_callback, cb_kwargs={
                                         "web_portal": self.web_portals[parsed_url.hostname]
                                     })

    def _send_preprocess_event(self, web_page_id: str) -> None:
        crawler_preprocessor_host = os.getenv("PREPROCESSOR_HOST")
        crawler_preprocessor_port = os.getenv("PREPROCESSOR_PORT", 5001)
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

    def _check_mail_javascript_tel_refs(self, url: str) -> bool:
        return 'javascript:void' in url or 'javascript.void' in url or 'mailto:' in url or 'tel:' in url or 'javascript:' in url

    def error_callback(self, failure):
        self.logger.warning('Get exception')
        self.logger.exception(failure)
        web_page_backlog = WebPageBacklog()
        web_page_backlog.error_text = repr(failure.value)
        web_page_backlog.error_code = str(failure.value.response)
        web_page_backlog.resource = failure.request.url
        web_portal = failure.request.cb_kwargs["web_portal"]
        web_page_backlog.web_portal = web_portal
        web_page_backlog.web_portal_name = web_portal.portal_name
        web_page_backlog.save()
