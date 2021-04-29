import unittest

import exceptions
from util.AppProperties import AppProperties


class TestProperties(unittest.TestCase):

    def test_host_and_port_miss(self):
        with self.assertRaises(exceptions.PropertiesException):
            props = AppProperties("test_app_json/missing_host_and_port_app.json")

    def test_db_miss(self):
        with self.assertRaises(exceptions.PropertiesException):
            props = AppProperties("test_app_json/missing_db_app.json")

    def test_empty_app_json(self):
        with self.assertRaises(exceptions.PropertiesException):
            props = AppProperties("test_app_json/empty_app.json")


if __name__ == "__main__":
    unittest.main()
