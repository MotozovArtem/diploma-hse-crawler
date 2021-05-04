from domain.Domain import WebPage, WebPortal
from domain.Database import Database
import requests
from urllib.parse import urlparse
from bs4 import BeautifulSoup

if __name__ == "__main__":
    target_url = urlparse("http://quotes.toscrape.com")

    response = requests.get("http://quotes.toscrape.com")

    web_portal = WebPortal()
    web_portal.domain_name = target_url.hostname
    soup = BeautifulSoup(response.text, "lxml")
    web_portal.portal_name = soup.find("meta").text
    web_portal.used_keywords = "[test1, test2, test3]"
    # web_portal.save()
