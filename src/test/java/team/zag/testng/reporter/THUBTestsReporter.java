package team.zag.testng.reporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.*;
import org.testng.xml.XmlSuite;
import team.zag.testng.pojo.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static team.zag.testng.utils.IConstants.SESSION_ID;

public class THUBTestsReporter implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        THUBTestsReporterOutput output = new THUBTestsReporterOutput();
        List<TestSuite> testSuites = new ArrayList<>();

        for (ISuite suite : suites) {
            TestSuite testSuite = new TestSuite();
            testSuite.setName(suite.getName());

            List<TestCase> testCases = new ArrayList<>();

            Map<String, ISuiteResult> suiteResults = suite.getResults();
            for (Map.Entry<String, ISuiteResult> entry : suiteResults.entrySet()) {

                List<BaseTestCase> skipped = new ArrayList<>();
                List<ExecutedTestCase> passed = new ArrayList<>();
                List<FailedTestCase> failed = new ArrayList<>();

                ITestContext testContext = entry.getValue().getTestContext();

                TestCase testCase = new TestCase();
                testCase.setName(entry.getKey());
                testCase.setDurationInMs(testContext.getEndDate().getTime() - testContext.getStartDate().getTime());
                testCase.setStartedAt(testContext.getStartDate());
                testCase.setFinishedAt(testContext.getEndDate());

                System.out.println("    Test>" + entry.getKey());

                // Failed
                IResultMap failedResult = testContext.getFailedTests();
                Set<ITestResult> testsFailed = failedResult.getAllResults();
                for (ITestResult testResult : testsFailed) {
                    FailedTestCase failedTestCase = new FailedTestCase();
                    failedTestCase.setMethodName(testResult.getName());
                    failedTestCase.setMessageThrown(testResult.getThrowable().getMessage());
                    failedTestCase.setSessionId((String) testResult.getTestContext().getAttribute(SESSION_ID));
                    failedTestCase.setClassName(testResult.getTestClass().getName());
                    failedTestCase.setParameters(testResult.getTestContext().getCurrentXmlTest().getAllParameters());
                    failed.add(failedTestCase);
                }

                // Passed
                IResultMap passResult = testContext.getPassedTests();
                Set<ITestResult> testsPassed = passResult.getAllResults();
                for (ITestResult testResult : testsPassed) {
                    ExecutedTestCase executedTestCase = new ExecutedTestCase();
                    executedTestCase.setMethodName(testResult.getName());
                    executedTestCase.setTookInMs((testResult.getEndMillis() - testResult.getStartMillis()));
                    executedTestCase.setSessionId((String) testResult.getTestContext().getAttribute(SESSION_ID));
                    executedTestCase.setClassName(testResult.getTestClass().getName());
                    executedTestCase.setParameters(testResult.getTestContext().getCurrentXmlTest().getAllParameters());
                    passed.add(executedTestCase);
                }

                // Skipped
                IResultMap skippedResult = testContext.getSkippedTests();
                Set<ITestResult> testsSkipped = skippedResult.getAllResults();
                for (ITestResult testResult : testsSkipped) {
                    BaseTestCase skippedTestCase = new BaseTestCase();
                    skippedTestCase.setMethodName(testResult.getName());
                    skippedTestCase.setClassName(testResult.getTestClass().getName());
                    skipped.add(skippedTestCase);
                }

                testCase.setSkipped(skipped);
                testCase.setPassed(passed);
                testCase.setFailed(failed);

                testCases.add(testCase);

            }
            testSuite.setTestCases(testCases);

            testSuites.add(testSuite);
        }
        output.setTestSuites(testSuites);

        try {
            ObjectMapper mapper = new ObjectMapper();
            String content = mapper.writeValueAsString(output);

            Path report = Paths.get(outputDirectory, "thub-testng-results.json");
            Files.createDirectories(report.getParent());
            Files.write(report, content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
