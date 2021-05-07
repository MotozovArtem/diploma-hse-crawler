import json

import exceptions


class AppProperties:
    def __init__(self, properties_file_name="app.json"):
        super().__init__()
        self.properties = None
        with open(properties_file_name, encoding="utf-8") as input_file:
            try:
                self.properties = json.load(input_file)
                if "db" not in self.properties:
                    raise exceptions.PropertiesException(
                        "\"db\" parameter missing in app.json")
                else:
                    if "name" not in self.properties["db"]:
                        raise exceptions.PropertiesException(
                            "\"name\" parameter missing in app.json")
                    if "host" not in self.properties["db"]:
                        raise exceptions.PropertiesException(
                            "\"host\" parameter missing in app.json")
                    if "port" not in self.properties["db"]:
                        raise exceptions.PropertiesException(
                            "\"port\" parameter missing in app.json")
                    if "user" not in self.properties["db"]:
                        raise exceptions.PropertiesException(
                            "\"user\" parameter missing in app.json")
                    if "password" not in self.properties["db"]:
                        raise exceptions.PropertiesException(
                            "\"password\" parameter missing in app.json")
            except exceptions.PropertiesException as e:
                raise exceptions.PropertiesException(str(e))
            except Exception as e:
                raise exceptions.PropertiesException(str(e))

    def __getitem__(self, item):
        return self.properties[item]

    def __contains__(self, item):
        return item in self.properties
