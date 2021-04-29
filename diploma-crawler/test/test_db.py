import datetime
import unittest

from model.Database import Database
from model.DomainClasses import Organization, OrganizationProcessing


class TestDb(unittest.TestCase):

    def test_connect(self):
        Database()

    def test_domain_save(self):
        db = Database()
        organization = Organization(name="MyOrganization",
                                    creation_date=datetime.datetime.now(),
                                    codes=[{"code": "value"}])
        organization.save()
        result = Organization.objects()
        self.assertEqual(len(result), 1)
        for obj in result:
            obj.delete()

    @unittest.skip
    def test_read_file(self):
        db = Database()
        organization_processing_list = OrganizationProcessing.objects()
        i = 0
        for organization_processing in organization_processing_list:
            collected_data_json = organization_processing.collected_data.read()
            i += 1
            data = str(collected_data_json, encoding="utf-8")
            with open("read_file_test_output_result_{0}.json".format(i), "w", encoding="utf-8") as out:
                out.write(data)


if __name__ == '__main__':
    unittest.main()
