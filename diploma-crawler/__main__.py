#!/usr/bin/python3
# -*- coding: utf-8 -*-
import argparse

from domain.Database import Database

args_parser = argparse.ArgumentParser(description="Illegal activity main script")
args_parser.add_argument("-ll", "--log_level", type=str, default="INFO", help="Log level (default: INFO)")
args_parser.add_argument("-q", "--query", type=str, default="займ", help="Query to search (default: займ)")
args_parser.add_argument("-s", "--size", type=int, default=10, help="Max search result size (default: 10)")

args = args_parser.parse_args()

DB = Database()

import scrapper.main as scrapper_main

if __name__ == '__main__':
    print("Hello world!")
    print("Starting scrapping")
    scrapper_main.main()