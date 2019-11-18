class TestResult:
    def __init__(self):
        self.runCount = 0
        self.errorCount = 0
        self.startUpErrorCount = 0
    def testStarted(self):
        self.runCount = self.runCount + 1
    def testFailed(self):
        self.errorCount = self.errorCount + 1
    def startUpFailed(self):
        self.startUpErrorCount = self.startUpErrorCount + 1
    def summary(self):
        default = "%d run, %d failed" % (self.runCount, self.errorCount)
        if self.startUpErrorCount > 0:
            default += ", %d start-up failed" % self.startUpErrorCount
        return default

class TestSuite:
    def __init__(self):
        self.tests= []
    def add(self, testCase):
        self.tests.append(testCase)
    def run(self, result):
        for test in self.tests:
            test.run(result)
        return result

class TestCase:
    def __init__(self, name):
        self.name = name
    def run(self, result):
        result.testStarted()
        try:
            self.setUp()
        except:
            result.startUpFailed()
        else:
            try:
                method = getattr(self, self.name)
                method()
            except:
                result.testFailed()
        finally:
            self.tearDown()
        return result
    def setUp(self):
        pass
    def tearDown(self):
        pass

class TestCaseTest(TestCase):
    def setUp(self):
        self.result = TestResult()
    def testTemplateMethod(self):
        test = WasRun("testMethod")
        
        test.run(self.result)
        assert("setUp testMethod tearDown " == test.log)
    def testResult(self):
        test = WasRun("testMethod")
        
        test.run(self.result)
        assert("1 run, 0 failed" == self.result.summary())
    def testFailedResultFormatting(self):
        
        self.result.testStarted()
        self.result.testFailed()
        assert("1 run, 1 failed" == self.result.summary())
    def testFailedResult(self):
        test = WasRun("testBrokenMethod")
        
        test.run(self.result)
        assert("1 run, 1 failed" == self.result.summary())
        assert(test.log == "setUp testMethod tearDown ")
    def testFailedSetUp(self):
        test = BrokenSetUpWasRun("testMethod")
        
        test.run(self.result)
        assert("1 run, 0 failed, 1 start-up failed" == self.result.summary())
        assert(test.log == "setUp tearDown ")
    def testSuite(self):
        suite = TestSuite()
        suite.add(WasRun("testMethod"))
        suite.add(WasRun("testBrokenMethod"))
        
        suite.run(self.result)
        assert("2 run, 1 failed" == self.result.summary())

class WasRun(TestCase):
    def __init__(self, name):
        TestCase.__init__(self, name)
    def testMethod(self):
        self.log = self.log + "testMethod "
    def testBrokenMethod(self):
        self.log = self.log + "testMethod "
        raise Exception
    def setUp(self):
        self.log = "setUp "
    def tearDown(self):
        self.log = self.log + "tearDown "

class BrokenSetUpWasRun(WasRun):
    def __init__(self, name):
        WasRun.__init__(self, name)
    def setUp(self):
        self.log = "setUp "
        raise Exception


suite = TestSuite()
suite.add(TestCaseTest("testTemplateMethod"))
suite.add(TestCaseTest("testResult"))
suite.add(TestCaseTest("testFailedResult"))
suite.add(TestCaseTest("testFailedResultFormatting"))
suite.add(TestCaseTest("testSuite"))
suite.add(TestCaseTest("testFailedSetUp"))
result = TestResult()
suite.run(result)
print(result.summary())