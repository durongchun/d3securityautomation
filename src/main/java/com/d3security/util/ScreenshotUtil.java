package com.d3security.util;

import bsh.This;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

import com.d3security.basetest.BaseTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Screenshot tool
 *
 */
@Slf4j
public class ScreenshotUtil {
    /**
     * Save path for screenshot
     */
    private static String SCREENSHOT_PATH = System.getProperty("user.dir") + File.separator + "target" + File.separator + "test-output" + File.separator + "screenshot";

    /**
     * Screenshot
     *
     * @param iTestResult 
     */
    public static void capture(ITestResult iTestResult) {
    	
        log.info("Starts screenshot");
        // Get driver for screenshot
        WebDriver driver = ((BaseTest) iTestResult.getInstance()).driver;
        // Screenshot path
        File screenshotFile = new File(SCREENSHOT_PATH);
        // Create a file if no file directory
        if (!screenshotFile.exists() && !screenshotFile.isDirectory()) {
            screenshotFile.mkdirs();
        }
        // Screenshot format
        String screenshotFormat = PropertiesReader.getKey("output.screenshot.format");
        // Which class caused the screenshot
        String className = iTestResult.getInstance().getClass().getSimpleName();
        // Date format
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMdHms");
        String timeStr = simpleDateFormat.format(new Date());
        // Screenshot name
        String screenshotName = className + "-" + timeStr;
        try {
            // Screenshot operation
            File sourcefile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // Screenshot save
            FileUtils.copyFile(sourcefile, new File(SCREENSHOT_PATH + File.separator + screenshotName + screenshotFormat));
        } catch (IOException e) {
            e.printStackTrace();
            log.error("The screenshot operation is exception!");
        }
    }
}