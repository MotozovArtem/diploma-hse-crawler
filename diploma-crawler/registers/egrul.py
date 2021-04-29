import urllib.request, requests, json, bs4, webbrowser
from openpyxl import load_workbook
from pymongo import MongoClient
from time import sleep

def check_egrul(check):
    s = requests.Session()
    r = s.get("https://egrul.nalog.ru/index.html")
    req = requests.Request(
        'POST',
        'https://egrul.nalog.ru/',
        data=b'vyp3CaptchaToken=&page=&query=' + check.encode() + b'&region=&PreventChromeAutocomplete=',
        headers={
            "Host": "egrul.nalog.ru",
            "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0",
            "Accept": "application/json, text/javascript, */*; q=0.01",
            "Accept-Language": "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3",
            "Accept-Encoding": "gzip, deflate, br",
            "Referer": "https://egrul.nalog.ru/index.html",
            "Content-Type": "application/x-www-form-urlencoded",
            "X-Requested-With": "XMLHttpRequest"
        }
    )
    r = s.prepare_request(req)
    r = s.send(r)
    item = json.loads(r.text)
    try:
        if item["ERRORS"] != '' and (item["ERRORS"])["captchaSearch"] != '':
            while True:
                r = s.get('https://egrul.nalog.ru/captcha-dialog.html',
                          headers={
                              "Host": "egrul.nalog.ru",
                              "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0",
                              "Accept-Language": "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3",
                              "Referer": "https://egrul.nalog.ru/index.html",
                              "Pragma": "no-cache",
                              "Cache-Control": "no-cache"
                          })
                b = bs4.BeautifulSoup(r.content.decode(), features="lxml").find('div', class_='field-data').find(
                    'img').get('src')
                webbrowser.open('https://egrul.nalog.ru' + b)
                ct = b.split('?a=')[1].split('&')[0]
                captcha = input('Введите капчу: ')
                r = requests.Request(
                    'POST',
                    'https://egrul.nalog.ru/captcha-proc.json',
                    data=b'captcha=' + captcha.encode() + b'&captchaToken=' + ct.encode(),
                    headers={
                        "Host": "egrul.nalog.ru",
                        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0",
                        "Accept": "application/json, text/javascript, */*; q=0.01",
                        "Accept-Language": "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3",
                        "Accept-Encoding": "gzip, deflate",
                        "Referer": "https://egrul.nalog.ru/index.html"
                    }
                )
                r = s.prepare_request(req)
                r = s.send(r)
                item = json.loads(r.text)
                try:
                    tr = False
                    if item["ERRORS"] != '':
                        tr = True
                except Exception as e:
                    pass
                if not tr:
                    break
    except Exception as e:
        pass
    t = json.loads(r.text)['t']
    sleep(0.5)
    r = s.get("https://egrul.nalog.ru/search-result/" + str(t),
              headers={
                  "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0",
                  "Accept-Language": "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3",
                  "Referer": "https://egrul.nalog.ru/index.html"
              }
              )
    jsn = json.loads(r.text)
    res_j = {}
    try:
        while True:
            if jsn['status'] != 'wait': break
            sleep(0.2)
    except Exception:
        pass
    try:
        item = (jsn["rows"])[0]
        res_j.update({"f_name": item['n']})
        try:
            res_j.update({"name": item['c']})
        except Exception:
            pass
        try:
            res_j.update({"address": item['a']})
        except Exception:
            pass
        res_j.update({"INN": item['i']})
        res_j.update({"OGRN": item['o']})
        try:
            res_j.update({"KPP": item['p']})
        except Exception:
            pass
        res_j.update({"dt": item['r']})
        try:
            res_j.update({"end_dt": item['e']})
        except Exception:
            pass
    except Exception as e:
        pass
    result = json.dumps(res_j, ensure_ascii=False)
    itog = json.loads(result)
    return itog
