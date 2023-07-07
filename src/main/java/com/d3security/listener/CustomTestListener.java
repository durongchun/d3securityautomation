package com.d3security.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.uncommons.reportng.HTMLReporter;

public class CustomTestListener extends HTMLReporter implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        log("========== Start of Test Method ==========");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log("[INFO] Test Method Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log("[ERROR] Test Method Failed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log("[INFO] Test Method Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log("========== End of Test Suite ==========");
    }

    private void log(String message) {
        Reporter.log(message, true);
    }
}

