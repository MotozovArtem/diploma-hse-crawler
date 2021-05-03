import uuid

from mongoengine import *
import datetime

class DomainObject(Document):
    '''Абстрактный класс доменного объекта'''
    __id = UUIDField(required=True, binary=False, default=uuid.uuid4, primary_key=True)
    last_modified_time = DateTimeField(default=datetime.datetime.utcnow)
    creation_time = DateTimeField(default=datetime.datetime.utcnow)

    meta = {'allow_inheritance': True, 'abstract': True}

class WebPortal(DomainObject):
    '''Доменный класс WebPortal'''
    portal_name = StringField(max_length=500)
    domain_name = StringField(max_length=255, unique=True)
    used_keywords = StringField(max_length=2000)


class WebPageAnalyseResult(DomainObject):
    '''Доменный класс WebPageAnalyseResult'''
    lemmatization_result = StringField()
    normalization_result = StringField()
    raw_text = StringField()
    error_text = StringField()
    start_analyse = DateTimeField()
    finish_analyse = DateTimeField()
    phase = StringField()


class WebPage(DomainObject):
    '''Доменный класс WebPage.'''
    url = StringField(max_length=1000)
    resource_name = StringField(max_length=500)
    meta = StringField(max_length=2000)
    head = StringField(max_length=1000)
    web_portal = ReferenceField(WebPortal)
    web_portal_analyse_result = ReferenceField(WebPageAnalyseResult)
