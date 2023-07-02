package com.d3security.listener;

import bsh.This;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.d3security.util.ScreenshotUtil;

/**
 * Test log listener
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20
 */
@Slf4j
public class TestLogListener extends TestListenerAdapter {
    /**
     * Start
     *
     * @param iTestContext ITestContext
     */
    @Override
    public void onStart(ITestContext iTestContext) {
        super.onStart(iTestContext);
        log.info(String.format("====================%s Start====================", iTestContext.getName()));
    }

    /**
     * Test starts
     *
     * @param iTestResult ITestResult
     */
    @Override
    public void onTestStart(ITestResult iTestResult) {
        super.onTestStart(iTestResult);
        log.info(String.format("========%s.%s Test Starts========", iTestResult.getInstanceName(), iTestResult.getName()));
    }

    /**
     * Test pass
     *
     * @param iTestResult ITestResult
     */
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        super.onTestSuccess(iTestResult);
        log.info(String.format("========%s.%s Test Pass========", iTestResult.getInstanceName(), iTestResult.getName()));
    }

    /**
     * Test fail
     *
     * @param iTestResult ITestResult
     */
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        super.onTestFailure(iTestResult);
        log.error(String.format("========%s.%s Test Fail, Why it fail：\n%s========", iTestResult.getInstanceName(), iTestResult.getName(), iTestResult.getThrowable()));
        // Throw an exception when it fails and take a screenshot
        ScreenshotUtil.capture(iTestResult);
    }

    /**
     * Test skip
     *
     * @param iTestResult ITestResult
     */
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        super.onTestSkipped(iTestResult);
        log.info(String.format("========%s.%s Test Skip========", iTestResult.getInstanceName(), iTestResult.getName()));
    }

    /**
     * Test End
     *
     * @param iTestContext ITestContext
     */
    @Override
    public void onFinish(ITestContext iTestContext) {
        super.onFinish(iTestContext);
        log.info(String.format("====================%s Test End====================", iTestContext.getName()));
    }
}