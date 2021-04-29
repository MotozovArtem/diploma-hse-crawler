import uuid

from mongoengine import *
import datetime

STATUS = ('ILLEGAL',
          'LEGAL')

PHASE = ('CREATED',
         'PROCESSING',
         'PROCESSED',
         'CHECKING',
         'FINISHED',
         'ERROR')


class DomainObject(Document):
    _inner_id = UUIDField(required=True, binary=False, default=uuid.uuid4)
    _last_modified_ts = DateTimeField(default=datetime.datetime.utcnow)
    _creation_date = DateTimeField(default=datetime.datetime.utcnow)

    meta = {'allow_inheritance': True}


class Organization(DomainObject):
    name = StringField()
    creation_date = DateTimeField()
    codes = ListField(DictField())


class CollectedResource(EmbeddedDocument):
    resource = StringField(required=True)
    data = StringField(required=True)


class CollectedDataByOrganization(DomainObject):
    collection_id = UUIDField(binary=False, default=uuid.uuid4)
    url = URLField()
    resources = ListField(EmbeddedDocumentField(CollectedResource))


class OrganizationProcessing(DomainObject):
    organization = ReferenceField(Organization)
    collected_data = FileField()
    classified = StringField()
    phase = StringField(choices=PHASE)
    error = StringField()
    status = StringField(choices=STATUS)
