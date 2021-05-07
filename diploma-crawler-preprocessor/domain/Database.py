import logging
import os

from mongoengine import connect, disconnect
from util.AppProperties import AppProperties

class Database:
    def __init__(self):
        self.__LOG = logging.getLogger("Database")
        app_properties = AppProperties()
        db_address_template = "mongodb://{0}:{1}/?authSource=admin"
        host_address_env = os.getenv('MONGO_DB_HOST')
        if host_address_env is None:
            host_address = app_properties["db"]["host"]
        else:
            host_address = host_address_env
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
