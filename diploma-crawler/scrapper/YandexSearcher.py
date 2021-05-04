import logging
import requests

from bs4 import BeautifulSoup
from http import HTTPStatus
from scrapper.constants import USER_AGENT


class YandexSearcher:
    def __init__(self, header: dict = None):
        self.__LOG: logging.Logger = logging.getLogger("Yandex-Searcher")
        self.url: str = "https://yandex.ru/search/?text={0}&lr=213"
        self.query: list = None
        if header is not None:
            if "User-Agent" not in header.keys():
                raise KeyError
        self.request_headers = None

    def get_query(self) -> list:
        return self.query

    def search(self, query: list) -> list:
        self.__LOG.info("Start searching")
        self.query: str = [str(elem) for elem in query]
        request_url: str = self.url.format("+".join(self.query))
        self.__LOG.info("Searching URL %s", request_url)
        response = requests.get(request_url, headers=self.request_headers)

        result = []

        self.__LOG.info("Response code %s", response.status_code)
        # HTTP OK = 200
        if response.status_code == HTTPStatus.OK:
            soup = BeautifulSoup(response.content, "html.parser")
            # todo: Какая-то проблема с капчей на Yandex.ru
            #  стоит подумать о других поисковых системах
            for title in soup.find_all("h2", class_="organic__title-wrapper"):
                anchors = title.find_all("a")
                if anchors:
                    link = anchors[0]["href"]
                    result.append(link)
        result = self.__clear_ads_references(result)
        return result

    def set_headers(self, headers: dict):
        if "User-Agent" not in headers.keys():
            raise KeyError
        self.request_headers = headers

    def __clear_ads_references(self, search_result: list) -> list:
        '''Очищает результат запроса к инф. поисковой системе, от рекламных ссылок.\n
        В данный момент поддерживает только YANDEX 
        
        search_result: list - результат запрос к инф. поисковой системе
        Возвращает список сайтов (list), без рекламных ссылок'''
        if self.search_type == Type.YANDEX:
            # In Yandex responses that AD contains yabs domain
            return list(filter(lambda site: "yabs." not in site, search_result))
        else:
            return search_result
