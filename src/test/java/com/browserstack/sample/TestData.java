package com.browserstack.sample;

import java.util.List;

public class TestData {
    private String testName;
    private String testFunctionName;
    private List<TestParameters> testParameters;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestFunctionName() {
        return testFunctionName;
    }

    public void setTestFunctionName(String testFunctionName) {
        this.testFunctionName = testFunctionName;
    }

    public List<TestParameters> getTestParameters() {
        return testParameters;
    }

    public void setTestParameters(List<TestParameters> testParameters) {
        this.testParameters = testParameters;
    }




    @Override
    public String toString() {
        return "TestData{" +
                "testName='" + testName + '\'' +
                ", testFunctionName='" + testFunctionName + '\'' +
                ", testParameters=" + testParameters +
                '}';
    }

}
