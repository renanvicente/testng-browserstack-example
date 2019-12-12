package team.zag.testng.pojo;

import java.util.List;

public class THUBTestsReporterOutput {
    private List<TestSuite> testSuites;

    public List<TestSuite> getTestSuites() {
        return testSuites;
    }

    public void setTestSuites(List<TestSuite> testSuites) {
        this.testSuites = testSuites;
    }

    @Override
    public String toString() {
        return "THUBTestsReporterOutput{" +
                "testSuites=" + testSuites +
                '}';
    }
}
