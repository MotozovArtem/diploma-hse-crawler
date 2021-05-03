#!/usr/bin/python3
# -*- coding: utf-8 -*-
import argparse
import logging

from domain.Database import Database
from multiprocessing.pool import Pool

args_parser = argparse.ArgumentParser(
    description="Сервис поиска, сбора и хранения данных о сайте.")
args_parser.add_argument("-ll", "--log_level", type=str, default="INFO",
                         help="Уровень логирования. (по умолчанию: INFO)")
args_parser.add_argument("-q", "--query", action="append", default=["займ"],
                         help="Ключевые слова для поиска сайтов. (по умолчанию: займ)")
args_parser.add_argument("-s", "--size", type=int, default=2,
                         help="Количество обрабатываемых сайтов. (по умолчанию: 2)")
args_parser.add_argument("-wp", "--web_portal", type=str,
                         help="Доменное имя для сбора данных для конкретного сайта.")

args = args_parser.parse_args()

LOG = logging.getLogger(__name__)
LOG.setLevel(logging.INFO)
LOG.propagate = False

LOG_FORMATTER = logging.Formatter(
    "%(asctime)s %(name)s [%(threadName)s]: %(levelname)s %(message)s")

FILE_LOGGING_HANDLER = logging.FileHandler("trace.log")
FILE_LOGGING_HANDLER.setLevel(logging.DEBUG)
FILE_LOGGING_HANDLER.setFormatter(LOG_FORMATTER)

CONSOLE_LOGGING_HANDLER = logging.StreamHandler()
CONSOLE_LOGGING_HANDLER.setLevel(logging._nameToLevel[args.log_level])
CONSOLE_LOGGING_HANDLER.setFormatter(LOG_FORMATTER)

LOG.addHandler(FILE_LOGGING_HANDLER)
LOG.addHandler(CONSOLE_LOGGING_HANDLER)

DB = Database()


def main(args):
    from scrapper.Searcher import Searcher, USER_AGENT
    from scrapper.ScrapperProcess import ScrapperProcess
    import random

    search = Searcher()
    user_agent = random.choice(USER_AGENT)
    LOG.debug("Set User-Agent header as %s", user_agent)
    search.set_headers({"User-Agent": user_agent})

    query = set(args.query)
    LOG.info("Started searching with query %s", " ".join(query))
    result = search.search(query)
    LOG.info("Search result: %s", ",".join(result))



if __name__ == '__main__':
    LOG.info("Start main process")
    main(args)
