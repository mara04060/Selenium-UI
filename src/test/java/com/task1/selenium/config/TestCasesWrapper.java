package com.task1.selenium.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.task1.utils.testCases;

import java.util.List;

public class TestCasesWrapper {
    @JsonProperty("testCase")
    private List<testCases> testCases;

    public List<testCases> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<testCases> testCases) {
        this.testCases = testCases;
    }
}
