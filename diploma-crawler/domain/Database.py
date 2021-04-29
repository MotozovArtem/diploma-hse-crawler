import logging

from mongoengine import connect, disconnect
from util.AppProperties import AppProperties

class Database:
    def __init__(self):
        app_properties = AppProperties()
        db_address_template = "mongodb://{0}:{1}/?authSource=admin"
        host_address = app_properties["db"]["host"]
        port = app_properties["db"]["port"]
        db_name = app_properties["db"]["name"]
        logging.info("Database URI address: %s", db_address_template.format(host_address, port))
        connect(db=db_name,
                username=app_properties["db"]["user"],
                password=app_properties["db"]["password"],
                host=db_address_template.format(host_address, port))
        logging.info("Connected to databases")

    def __del__(self):
        disconnect()
        logging.info("Closed connection to database")
