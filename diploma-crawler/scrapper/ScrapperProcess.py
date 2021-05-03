import argparse
import logging
import sys
from urllib.parse import urlparse

from scrapy.crawler import CrawlerProcess
from scrapy.utils.project import get_project_settings

from domain.Database import Database
from scrapper.Searcher import Searcher, USER_AGENT
from scrapper.spider.spiders.spider import Spider

class ScrapperProcess:
    """description of class"""
    def __init__(self, web_portal: str):
        self.__web_portal: str = web_portal
        self.__LOG: logging.Logger = logging.getLogger("ScrapperProcess-{}".format(self.__web_portal))
    
    def run(self) -> None:
        self.__LOG.info("Started")

        result = ["http://quotes.toscrape.com", "http://books.toscrape.com/"]
        #result = ["https://car.bistrodengi.ru/",
        #          "https://creditplus.ru/",
        #          "https://web-zaim.ru/",
        #          "https://turbozaim.ru/",
        #          "https://dozarplati.com/",
        #          "https://fastmoney.ru/",
        #          "https://www.kredito24.ru/",
        #          ]

        Spider.start_urls = [self.__web_portal]
        Spider.allowed_domains = urlparse(self.__web_portal).hostname
        process = CrawlerProcess(get_project_settings())
        process.crawl(Spider)
        process.start()
        process.join()


