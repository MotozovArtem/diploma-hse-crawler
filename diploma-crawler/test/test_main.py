import unittest

class TestMain(unittest.TestCase):
    
    def test_main(self):
        self.assertEqual('Hello world'.upper(), 'HELLO WORLD')


if __name__ == "__main__":
    unittest.main()