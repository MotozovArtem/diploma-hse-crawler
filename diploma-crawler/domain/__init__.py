import uuid

from mongoengine import *
import datetime

class DomainObject(Document):
    '''
    Абстрактный класс доменного объекта
    '''
    __id = UUIDField(required=True, binary=False, default=uuid.uuid4)
    _last_modified_time = DateTimeField(default=datetime.datetime.utcnow)
    _creation_time = DateTimeField(default=datetime.datetime.utcnow)

    meta = {'allow_inheritance': True}

class WebPortal(DomainObject):
    '''
    Доменный класс WebPortal
    '''
    pass

class WebPage(DomainObject):
    '''Доменный класс WebPage'''
    pass


class WebPageAnaliseResult(DomainObject):
    '''Доменный класс WebPageAnaliseResult'''
    pass
