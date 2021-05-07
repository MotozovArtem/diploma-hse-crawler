from mongoengine import signals
from mongoengine import StringField, UUIDField, DateTimeField, ReferenceField, IntField, Document

import uuid
import datetime

ANALYSE_PHASE = {
    "ANALYSE_START": "Анализ начат",
    "ANALYSE_FINISHED": "Анализ закончен",
    "LEMMATIZATION": "Стадия лемматизации",
    "NORMALIZATION": "Стадия нормализации",
    "ANALYSE_FAILED": "Ошибка анализа"
}

class DomainObject(Document):
    '''Абстрактный класс доменного объекта'''
    __id = UUIDField(required=True, binary=False,
                     default=uuid.uuid4, primary_key=True)
    last_modified_time = DateTimeField(default=datetime.datetime.utcnow)
    creation_time = DateTimeField(default=datetime.datetime.utcnow)
    ts = IntField(default=0)

    meta = {'allow_inheritance': True, 'abstract': True}


class WebPortal(DomainObject):
    '''Доменный класс WebPortal'''
    _class = StringField(default="ru.hse.diploma.domain.WebPortal")
    portal_name = StringField(max_length=500)
    domain_name = StringField(max_length=255, unique = True)
    used_keywords = StringField(max_length=2000)

    meta = {'collection': "web_portals"}


class WebPageAnalyseResult(DomainObject):
    '''Доменный класс WebPageAnalyseResult'''
    _class = StringField(default="ru.hse.diploma.domain.WebPageAnalyseResult")
    lemmatization_result = StringField()
    normalization_result = StringField()
    raw_text = StringField()
    error_text = StringField()
    start_analyse = DateTimeField()
    finish_analyse = DateTimeField()
    phase = StringField()
    web_page = ReferenceField("WebPage")

    meta = {'collection': 'web_page_analyse_results'}


class WebPage(DomainObject):
    '''Доменный класс WebPage.'''
    _class = StringField(default="ru.hse.diploma.domain.WebPage")
    url = StringField(max_length=1000)
    resource_name = StringField(max_length=500)
    meta_data = StringField(max_length=2000)
    head = StringField(max_length=1000)
    page_text = StringField()
    web_portal = ReferenceField("WebPortal")
    web_portal_analyse_result = ReferenceField("WebPageAnalyseResult")

    meta = {'collection': 'web_pages'}


def update_last_modified_time(sender, document):
    document.last_modified_time = datetime.datetime.utcnow


def update_version_ts(sender, document):
    document.ts += 1


signals.pre_save.connect(update_last_modified_time)
signals.pre_save.connect(update_version_ts)
