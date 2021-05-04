from flask import Flask
from flask import request
from flask import Response
from flask.logging import default_handler
import logging

app = Flask(__name__)

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
        target_url: str = request_parameters["target"]
        app.logger.info("Receive request with - %s", target_url)
        return Response(status=200)
    else:
        app.logger.info("Received non-json data")
        return Response(status=406)
