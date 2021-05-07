from domain.Database import Database
from domain.Domain import WebPage, WebPageAnalyseResult, ANALYSE_PHASE
from flask import Flask
from flask import Flask
from flask import request
from flask import Response
from flask.logging import default_handler
from rq import Queue
from rq import Worker
import logging
import requests
import redis
import datetime
import os

app = Flask(__name__)

REDIS_HOST = os.getenv("REDIS_HOST", default='localhost')

r = redis.Redis(host=REDIS_HOST)

q = Queue(connection=r)

DB = Database()

LOG_FORMATTER = logging.Formatter(
    "%(asctime)s %(name)s [%(threadName)s]: %(levelname)s %(message)s")

FILE_LOGGING_HANDLER = logging.FileHandler("trace.log")
FILE_LOGGING_HANDLER.setLevel(logging.DEBUG)
FILE_LOGGING_HANDLER.setFormatter(LOG_FORMATTER)
default_handler.setLevel(logging.INFO)


def get_web_page(web_page_id: str) -> WebPage:
    return WebPage.objects.get(pk=web_page_id)


def preprocess_webpage(web_page_id: str) -> None:
    app.logger.info(
        "Started preprocessing for WebPage with id: %s", web_page_id)
    web_page = get_web_page(web_page_id)
    if web_page is not None:
        app.logger.info(
            "Loaded from db WebPage object with id: %s", web_page_id)
        web_page_analyse_result = WebPageAnalyseResult()
        web_page_analyse_result.start_analyse = datetime.datetime.utcnow()
        web_page_analyse_result.phase = "ANALYSE_START"
        web_page_analyse_result.web_page = web_page
        web_page_analyse_result.save()


@app.route("/register_preprocess_task", methods=["POST"])
def start_crawl():
    if request.is_json:
        request_parameters = request.get_json(cache=False)
        web_page_id = request_parameters["web_page_id"]
        task = q.enqueue(preprocess_webpage, web_page_id)
        jobs = q.jobs
        queue_len = len(q)
        app.logger.info("Task queued at %s.",
                        task.enqueued_at.strftime('%a, %d %b %Y %H:%M:%S'))
        app.logger.info("Queue length: %s", queue_len)
        return Response(status=200)
    else:
        app.logger.info("Received non-json data")
        return Response(status=406)
