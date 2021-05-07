from domain.Database import Database
from domain.Domain import WebPage

DB = Database()

print(WebPage.objects.get(pk='021c037e-fc03-41fb-b48f-99f20127940e'))
