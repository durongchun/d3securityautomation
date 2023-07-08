package com.d3security.util;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

/**
 * These are 'hard' stepInfo, failures results in an AssertionError which halts
 * / fails the test immediately. See the parallel class Validate for equivalent
 * methods that allow a test to continue, but fail at the end of the test.
 *
 * @author archanac
 */
public class Verify extends StepInfo {

	/**
	 * Verify two objects are equal
	 *
	 * @param actual      first object to compare
	 * @param expected    second object to compare
	 * @param description a message indicating the expected equality
	 */
	@Deprecated
	public static void verifyEquals(final Object actual, final Object expected, final String description) {
		addMessage("<b><font color=\"brown\">Please pass in the driver to the verifyEquals method</font></b>");
		addStep();
		try {
			// see if the two objects are equal
			Assert.assertEquals(actual, expected, description);
			Reporter.log(formattedMessage(true, description));
			// else catch the error and create a formatted message and fail it
		} catch (AssertionError assertionError) {
			String errorMessage = String.format("%s -  %s does NOT equal to %s", description, actual, expected);
			failTest(errorMessage, assertionError);
		}
	}

	/**
	 * Verify two objects are equal
	 *
	 * @param actual      first object to compare
	 * @param expected    second object to compare
	 * @param description a message indicating the expected equality
	 * @param driver      Driver that controls the browser
	 */
	public static void verifyEquals(final Object actual, final Object expected, final String description,
			WebDriver driver) {
		addStep();
		try {
			// see if the two objects are equal
			Assert.assertEquals(actual, expected, description);
			Reporter.log(formattedMessage(true, description));
			// else catch the error and create a formatted message and fail it
		} catch (AssertionError assertionError) {
			String errorMessage = String.format("%s -  %s does NOT equal to %s", description, actual, expected);
			failTest(errorMessage, driver);
		}
	}

	/**
	 * Verify two objects are not equal
	 *
	 * @param actual      first object to compare
	 * @param expected    second object to compare
	 * @param description a message indicating the expected equality
	 */
	@Deprecated
	public static void verifyNotEquals(final Object actual, final Object expected, String description) {
		addMessage("<b><font color=\"brown\">Please pass in the driver to the verifyNotEquals method</font></b>");
		addStep();
		try {
			Assert.assertNotEquals(actual, expected, description);
			Reporter.log(formattedMessage(true, description));
		} catch (AssertionError assertionError) {
			String errorMessage = String.format("%s -  %s does equal to %s", description, actual, expected);
			failTest(errorMessage, assertionError);
		}
	}

	/**
	 * Verify two objects are not equal
	 *
	 * @param actual      first object to compare
	 * @param expected    second object to compare
	 * @param description a message indicating the expected equality
	 * @param driver
	 */
	public static void verifyNotEquals(final Object actual, final Object expected, String description,
			WebDriver driver) {
		addStep();
		try {
			Assert.assertNotEquals(actual, expected, description);
			Reporter.log(formattedMessage(true, description));
		} catch (AssertionError assertionError) {
			String errorMessage = String.format("%s -  %s does equal to %s", description, actual, expected);
			failTest(errorMessage, driver);
		}
	}

	/**
	 * Verify that the 'stringToFind' substring is contained in the
	 * 'stringToEvaluate' string
	 *
	 * @param stringToEvaluate String to search in
	 * @param stringToFind     String to search for
	 */
	@Deprecated
	public static void verifyContains(final String stringToEvaluate, final String stringToFind,
			final String description) {
		addMessage("<b><font color=\"brown\">Please pass in the driver to the verifyContains method</font></b>");
		verifyTrue(stringToEvaluate.contains(stringToFind),
				String.format("Expected %s to contain %s", stringToEvaluate, stringToFind));
	}

	/**
	 * Verify that the 'stringToFind' substring is contained in the
	 * 'stringToEvaluate' string
	 *
	 * @param stringToEvaluate String to search in
	 * @param stringToFind     String to search for
	 * @param driver           the driver controlling the browser
	 */
	public static void verifyContains(final String stringToEvaluate, final String stringToFind, WebDriver driver) {
		verifyTrue(stringToEvaluate.contains(stringToFind),
				String.format("Expected %s to contain %s", stringToEvaluate, stringToFind), driver);
	}

	/**
	 * Verify that the 'stringToFind' substring is contained in the
	 * 'stringToEvaluate' string
	 *
	 * @param stringToEvaluate String to search in
	 * @param stringToFind     String to search for
	 * @param description
	 * @param driver
	 */
	public static void verifyContains(final String stringToEvaluate, final String stringToFind,
			final String description, WebDriver driver) {
		verifyTrue(stringToEvaluate.contains(stringToFind),
				String.format("%s\nExpected %s to contain %s", description, stringToEvaluate, stringToFind), driver);
	}

	/**
	 * Verify a condition evaluates to false
	 *
	 * @param condition   an evaluated conditional representing some sort of test.
	 * @param description a description describing the expected
	 */
	@Deprecated
	public static void verifyFalse(final boolean condition, final String description) {
		addMessage("<b><font color=\"brown\">Please pass in the driver to the verifyFalse method</font></b>");
		addStep();
		final String message = formattedMessage(!condition, description);
		// Check to see if the condition is false
		try {
			Assert.assertFalse(condition, description);
			Reporter.log(message);
			// else catch the failed comparison and create a formatted message
			// and fail it
		} catch (AssertionError assertionError) {
			failTest(message, assertionError);
		}
	}

	/**
	 * Verify a condition evaluates to false
	 *
	 * @param condition   an evaluated conditional representing some sort of test.
	 * @param description a description describing the expected
	 * @param driver      the driver controlling the browser
	 */
	public static void verifyFalse(final boolean condition, final String description, WebDriver driver) {
		addStep();
		final String message = formattedMessage(!condition, description);
		// Check to see if the condition is false
		try {
			Assert.assertFalse(condition, description);
			Reporter.log(message);
			// else catch the failed comparison and create a formatted message
			// and fail it
		} catch (AssertionError assertionError) {
			failTest(message, driver);
		}
	}

	/**
	 * Verify that a condition is true
	 *
	 * @param condition   expected condition to evaluate
	 * @param description a description describing the expected value
	 */
	@Deprecated
	public static void verifyTrue(final boolean condition, final String description) {
		addMessage("<b><font color=\"brown\">Please pass in the driver to the verifyTrue method</font></b>");
		addStep();
		try {
			Assert.assertTrue(condition, description);
			Reporter.log(formattedMessage(condition, description));
		} catch (AssertionError assertionError) {
			failTest(description, assertionError);
		}
	}

	/**
	 * Verify that a condition is true
	 *
	 * @param condition   expected condition to evaluate
	 * @param description a description describing the expected value
	 * @param driver      the driver controlling the browser
	 */
	public static void verifyTrue(final boolean condition, final String description, WebDriver driver) {
		addStep();
		try {
			Assert.assertTrue(condition, description);
			Reporter.log(formattedMessage(condition, description));
		} catch (AssertionError assertionError) {
			failTest(assertionError.toString(), driver);
		}
	}

	/**
	 * Verify that the actual list of strings contains all of of the expected
	 * strings. NOTE: This method does NOT confirm the order of list.
	 */
	@Deprecated
	public static void verifyContainsAll(List<String> actual, List<String> expected, String description) {
		addMessage("<b><font color=\"brown\">Please pass in the driver to the verifyContainsAll method</font></b>");
		addStep();
		String fullMessageText = String.format(
				formattedMessage(description)
						+ " All expected text strings contained in actual list.  Actual:  %s.  Expected:  %s.",
				actual, expected);
		if (actual.containsAll(expected)) {
			Reporter.log(formattedMessage(true, description));
		} else {
			failTest(fullMessageText);
		}
	}

	/**
	 * Verify that the actual list of strings contains all of of the expected
	 * strings. NOTE: This method does NOT confirm the order of list.
	 */
	public static void verifyContainsAll(List<String> actual, List<String> expected, String description,
			WebDriver driver) {
		addStep();
		String fullMessageText = String.format(
				formattedMessage(description)
						+ " All expected text strings contained in actual list.  Actual:  %s.  Expected:  %s.",
				actual, expected);
		if (actual.containsAll(expected)) {
			Reporter.log(formattedMessage(true, description));
		} else {
			failTest(fullMessageText, driver);
		}
	}

}
