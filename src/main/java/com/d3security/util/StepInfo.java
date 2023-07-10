package com.d3security.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.internal.Utils;

/**
 * <p>
 * This class provides basic reporting functionality by wrapping the TestNG
 * Assert class and Report class. This class will also manage total steps
 * executed per test and will provide the ability to record add messages to
 * assist with monitoring overall flow of a test case.
 * </p>
 * <br />
 * Created by Lucy
 */
public class StepInfo {
    private static final Logger logger = LogManager.getLogger(StepInfo.class);
    /*
     * Using a Thread local class for each thread to hold an implicit reference
     * to its copy of a thread-local variable as long as the thread is alive
     */
    private static ThreadLocal<Integer> steps = new ThreadLocal<Integer>() {
        @Override
        public Integer initialValue() {
            return 0;
        }
    };

    /*
     * Using a Thread local class for each thread to hold an implicit reference
     * to its copy of a thread-local variable as long as the thread is alive
     */
    private static ThreadLocal<Boolean> displayStepInfo = new ThreadLocal<Boolean>() {
        @Override
        public Boolean initialValue() {
            return true;
        }
    };

    /**
     * get the current step number and Reset the step count
     *
     * @return int
     */
    public static int getAndResetStepInfoCount() {
        int count = steps.get();
        steps.set(0);
        return count;
    }

    protected static void addStep() {
        if (displayStepInfo.get()) {
            steps.set(steps.get() + 1);
        }
    }

    public static void setDisplayStepInfo(boolean displayStepInfo1) {
        displayStepInfo.set(displayStepInfo1);
    }

    /**
     * Add a message to our Report. This method will also format the message by
     * adding a step # and classification as a message. If you don't want to
     * format the step see this.addMessage(final String message, final boolean
     * step)
     *
     * @param message a message to add the report
     */
    public static void addMessage(final String message) {
        addMessage(message, true);
    }

    /**
     * Add a message to the current test session report.
     *
     * @param message a message to add the report
     * @param step    boolean indicating if we should format, number the step.
     */
    private static void addMessage(final String message, final boolean step) {
        // if step is enabled, then we format the step else
        // we just add a message to the reporter log
        if (step) {
            addStep();
            Reporter.log(formattedMessage(message));
        } else {
            Reporter.log(formattedMessage(message));
        }
    }

    /**
     * Fail the current test and print the Assertion Error stack trace
     *
     * @param description a reason telling us why we failed the test
     */
    @Deprecated
    protected static void failTest(final String description, AssertionError assertionError) {
    	ScreenShooter.captureDesktopScreenshot();
        final String message = formattedMessage(false, description);
        Reporter.log(message);
       // Reporter.log(Utils.stackTrace(assertionError, false)[1]);
        if (assertionError.getCause() != null) {
            logger.error(message, assertionError.getCause());
        } else {
            logger.error(message);
        }
        Assert.fail(message);
    }

    /**
     * Fail the current test
     *
     * @param description a reason telling us why we failed the test
     */
    public static void failTest(final String description, WebDriver driver) {
    	ScreenShooter.capture(driver);  
    	final String message = formattedMessage(false, description);
        Reporter.log(message);
        logger.error(message);
        Assert.fail(message);
    }
    
    /**
     * Fail the current test without screenshot
     *
     * @param description a reason telling us why we failed the test
     */
    public static void failTestWithoutScreenshot(final String description) {
        final String message = formattedMessage(false, description);
        Reporter.log(message);
        logger.error(message);
        Assert.fail(message);
    }

    /**
     * Fail the current test
     *
     * @param description a reason telling us why we failed the test
     */
    @Deprecated
    public static void failTest(final Object description) {
        failTest(String.valueOf(description));
    }


    /**
     * Fail the current test
     *
     * @param description a reason telling us why we failed the test
     */
    @Deprecated
    public static void failTest(final String description) {
        ScreenShooter.captureDesktopScreenshot();
        final String message = formattedMessage(false, description
                + "\nIF THE SCREEN APPEARS BLACK PLEASE PASS IN THE DRIVER");
        Reporter.log(message);
        logger.error(message);
        Assert.fail(message);
    }

    /**
     * This method formats a message prior to adding it to our report
     *
     * @param condition   condition being checked
     * @param description a description to add to the message
     * @return a formatted message to add to the report
     */
    protected static String formattedMessage(final boolean condition, final String description) {
        if (displayStepInfo.get()) {
        	return String.format("[Step #%d] [%s]: %s", getSteps(), condition ? "Passed" : "<b><font color=\"red\">Failed</font></b>",
                    description);
        } else {
            return "";
        }
    }

    /**
     * This method formats a message prior to adding it to our report
     *
     * @param description a description to add to the message
     * @return a formatted message to add to the report
     */
    protected static String formattedMessage(final String description) {
        if (displayStepInfo.get()) {
            return String.format("[Step #%d] [Message]: %s", getSteps(), description);
        } else {
            return "";
        }
    }

    /**
     * Return the number of steps. (1 based)
     *
     * @return returns the total number of steps
     */
    private static int getSteps() {
        return steps.get();
    }
}