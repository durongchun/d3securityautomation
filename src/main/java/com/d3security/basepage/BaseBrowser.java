package com.d3security.basepage;

import static org.apache.log4j.LogManager.getLogger;

import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.d3security.util.PropertiesReader;
import com.d3security.util.RedisUtil;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptJob;

/**
 * Encapsulate the most basic operations on the interface in the browser
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20
 */
public class BaseBrowser {
	/**
	 * Driver
	 */
	protected WebDriver driver;

	/**
	 * Action
	 */
	protected Actions actions;

	/**
	 * J
	 */
	protected JavascriptExecutor js;

	/**
	 * Explicit Waits
	 */
	protected WebDriverWait wait;

	/**
	 * Redis connection tool
	 */
	protected RedisUtil redisUtil;

	SqlSession sqlSession = null;

	/**
	 * Constructor 1
	 *
	 * @param driver drive
	 */
	public BaseBrowser(WebDriver driver) {
		this.driver = driver;
		this.actions = new Actions(driver);
		this.js = ((JavascriptExecutor) driver);
		// display waiting time
		long timeout = Long.parseLong(PropertiesReader.getKey("driver.timeouts.webDriverWait"));
		this.wait = new WebDriverWait(driver, timeout);
	}

	/**
	 * Constructor 2
	 *
	 * @param driver
	 * @param redisUtil redis connection tool
	 */
	public BaseBrowser(WebDriver driver, RedisUtil redisUtil) {
		this.driver = driver;
		this.actions = new Actions(driver);
		this.js = ((JavascriptExecutor) driver);
		// Display waiting time
		long timeout = Long.parseLong(PropertiesReader.getKey("driver.timeouts.webDriverWait"));
		this.wait = new WebDriverWait(driver, timeout);
		this.redisUtil = redisUtil;
	}

	/*
	 * ============================== Basic Element Operations
	 * ==============================
	 */

	/**
	 * Get WebElement element object through element locating
	 *
	 * @param locator By
	 * @return located element
	 */
	public WebElement locateElement(By locator) {
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * Click element
	 *
	 * @param locator By
	 * @return clicked element
	 */
	public WebElement clickElement(By locator) {
		WebElement buttonElement = locateElement(locator);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		highlightElement(driver, buttonElement);
		buttonElement.click();
		return buttonElement;
	}

	/**
	 * Input box
	 *
	 * @param locator By
	 * @return input element
	 */
	public WebElement sendInput(By locator, CharSequence... content) {
		WebElement inputElement = locateElement(locator);
		highlightElement(driver, locator);
		inputElement.clear();
		inputElement.sendKeys(content);
		return inputElement;
	}

	/**
	 * Move to element
	 *
	 * @param locator
	 */
	public void moveToElement(By locator) {
		actions.moveToElement(locateElement(locator)).perform();
	}

	/**
	 * Drag and drop element
	 *
	 * @param fromLocator
	 * @param toLocator
	 */
	public void dragAndDropElement(By fromLocator, By toLocator) {
		wait.until(ExpectedConditions.elementToBeClickable(fromLocator));
		actions.dragAndDrop(locateElement(fromLocator), locateElement(toLocator)).perform();
	}

	public void selectDropdownByVisibleText(By locator, String value) {
		// Locate the dropdown element
		WebElement dropdownElement = driver.findElement(locator);
		// Create a Select object
		Select dropdown = new Select(dropdownElement);	
		// Select an option by value
		dropdown.selectByVisibleText(value);		
		// dropdown.selectByIndex(2);
		//dropdown.selectByValue();
		
		
	}

	/**
	 * Go to a page
	 */
	public void enterPage(String url) {
		driver.get(url);
	}

	/*
	 * ============================== Switch Window Handle
	 * ==============================
	 */

	/**
	 * Find next window, if only have one tab
	 *
	 * @return drive
	 */
	public WebDriver switchNextHandle() {
		// Current window
		String currentHandle = driver.getWindowHandle();
		// all windows
		Set<String> allHandlesSet = driver.getWindowHandles();
		// find next window
		for (String handle : allHandlesSet) {
			if (!handle.equals(currentHandle)) {
				return driver.switchTo().window(handle);
			}
		}
		return driver;
	}

	/**
	 * Muti-windows switching according to parameter:num
	 *
	 * @param num starts from 1
	 * @return drive
	 */
	public WebDriver switchHandleByNum(int num) {
		// All windows
		Set<String> allHandlesSet = driver.getWindowHandles();
		Object[] allHandlesArr = allHandlesSet.toArray();
		// Switch to specific window
		return driver.switchTo().window(allHandlesArr[num - 1].toString());
	}

	/**
	 * muti-windows switching according to parameter:window title
	 *
	 * @param title contains(window title)
	 * @return drive
	 * @throws Exception if it couldn't find the specific window
	 */
	public WebDriver switchHandleByTitle(String title) throws Exception {
		// Current window
		String currentHandle = driver.getWindowHandle();
		// All windows
		Set<String> allHandlesSet = driver.getWindowHandles();
		// Find the first window title
		for (String handle : allHandlesSet) {
			driver.switchTo().window(handle);
			if (driver.getTitle().contains(title)) {
				return driver;
			}
		}
		driver.switchTo().window(currentHandle);
		throw new Exception(title + "window handle is not existing");
	}

	/**
	 * Muti-windows switching according to parameter:window url
	 *
	 * @param url contains(window url)
	 * @return drive
	 * @throws Exception if it couldn't find the specific window
	 */
	public WebDriver switchHandleByUrl(String url) throws Exception {
		// Current window
		String currentHandle = driver.getWindowHandle();
		// All windows
		Set<String> allHandlesSet = driver.getWindowHandles();
		// Find the first window url
		for (String handle : allHandlesSet) {
			driver.switchTo().window(handle);
			if (driver.getCurrentUrl().contains(url)) {
				return driver;
			}
		}
		driver.switchTo().window(currentHandle);
		throw new Exception(url + "window handle is not existing");
	}

	/* ============================== Switch Frame ============================== */

	/**
	 * Switch frame according to element located element
	 *
	 * @param locator frame
	 * @return drive
	 */
	public WebDriver switchFrame(By locator) {
		return driver.switchTo().frame(locateElement(locator));
	}

	/**
	 * Switch to parent frame
	 *
	 * @return drive
	 */
	public WebDriver switchParentFrame() {
		return driver.switchTo().parentFrame();
	}

	/**
	 * Switch out of frame
	 *
	 * @return drive
	 */
	public WebDriver switchOutOfFrame() {
		return driver.switchTo().defaultContent();
	}

	public void switchSecondWindow() {
		// get current window
		String currentWindowHandle = driver.getWindowHandle();

		// get all windows
		Set<String> windowHandles = driver.getWindowHandles();

		// switch to the second windows
		for (String handle : windowHandles) {
			if (!handle.equals(currentWindowHandle)) {
				driver.switchTo().window(handle);
				break;
			}
		}

	}

	/* ============================== JS Action ============================== */

	/**
	 * Execute JS scripts
	 *
	 * @param script JS
	 */
	public void executeScript(String script) {
		js.executeScript(script);
	}

	/**
	 * Execute JS scripts
	 *
	 * @param script JS
	 * @param args   object element array
	 */
	public void executeScript(String script, Object... args) {
		js.executeScript(script, args);
	}

	/**
	 * Overloading method executeScript with no webelements Param
	 *
	 * @param command
	 * @return
	 */
	private Object executeScriptOb(final String command) {
		return js.executeScript(command);
	}

	/**
	 * Javascript click element
	 */
	public void clickElementWithJavaScript(WebDriver driver, WebElement element) {
		highlightElement(driver, element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	/**
	 * Scroll to top of page
	 */
	public void scrollToTop() {
		executeScript("window.scrollTo(window.pageXOffset, 0)");
	}

	/**
	 * Scroll to bottom of page
	 */
	public void scrollToBottom() {
		executeScript("window.scrollTo(window.pageXOffset, document.body.scrollHeight)");
	}

	/**
	 * Scroll to align the element with the top of the window
	 *
	 * @param by, need the element which is aligned on the top of the window
	 */
	public void scrollElementTopToTop(By by) {
		executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
	}

	/**
	 * Scroll to align the element with the bottom of the windows
	 *
	 * @param by, need the element which is aligned on the bottom of the window
	 */
	public void scrollElementBottomToBottom(By by) {
		executeScript("arguments[0].scrollIntoView(false);", driver.findElement(by));
	}

	/**
	 * Scroll to right of page
	 */
	public void scrollToRight() {
		executeScript("window.scrollTo(document.body.scrollWidth, window.pageYOffset)");
	}

	/**
	 * Scroll to left of page
	 */
	public void scrollToLeft() {
		executeScript("0, window.pageYOffset)");
	}

	protected final Logger logger = getLogger(JavaScriptJob.class);

	/**
	 * Execute Mouse Over to an Element.
	 *
	 * @param element
	 */
	public void mouseOverToElement(WebElement element) {
		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); "
				+ "arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		executeScript(mouseOverScript, element);
	}

	/**
	 * Select a select's option based on a given value
	 *
	 * @param dropDown a web element representing a drop down
	 * @param value    a value to select that represents an option associated with
	 *                 the drop down
	 */
	public void selectDropDownByValueWithJavaScript(WebElement dropDown, Object value) {
		executeScript("$(arguments[0]).attr(\"selected\", \"selected\")",
				dropDown.findElements(By.cssSelector("option[value='" + value + "']")));
	}

	/**
	 * Set an element's value
	 *
	 * @param element a web element representing a field
	 * @param value   a value to set the field's value to
	 */
	public void setField(WebElement element, Object value) {
		executeScript("$(arguments[0]).val(\"" + value + "\");", element);
	}

	/**
	 * Check to see if jQuery is actually loaded
	 *
	 * @return
	 */
	private boolean isJqueryPresent() {
		return Boolean.parseBoolean(String.valueOf(executeScriptOb("return (typeof jQuery != 'undefined')")));
	}

	/**
	 * Wait for jQuery to load
	 */
	public void waitForJQueryToLoad() {
		int timeWaited = 0;
		while (!isJqueryPresent() && (timeWaited < 5)) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				logger.error(e);
			}
			++timeWaited;
		}
		if (timeWaited > 0) {
			logger.debug(String.format("Waiting %s milliseconds for jQuery to load", (timeWaited * 250)));
		} else {
			logger.warn("Did not have to wait for jQuery to load");
		}

	}

	/**
	 * Scroll to End Of Page whose length is known and is not dynamic
	 */
	public void scrollToEndOfPage() {
		String scrollOverCommand = "window.scrollTo(0, document.body.scrollHeight);";
		executeScript(scrollOverCommand);
	}

	/**
	 * Scroll Horizontally
	 *
	 * @param pixels number of pixels to scroll
	 */
	public void scrollHorizontally(int pixels) {
		executeScript("window.scrollBy(" + pixels + ",0)");
	}

	/**
	 * Click an element using native javascript. This is useful in instances where
	 * the webdriver can't click on an element using its built in API.
	 * 
	 * @param element a web element to invoke {@code click} on
	 */
	public void click(WebElement element) {
		highlightElement(driver, element);
		executeScript("arguments[0].click()", element);
	}

	/**
	 * @return if page is loaded
	 */
	public boolean isPageLoaded() {
		return executeScriptOb("return document.readyState").equals("complete");
	}

	/**
	 * Check if the page has refreshed or not
	 */
	public void waitForPageToRefresh() {
		int timeWaited = 0;
		while (!isPageLoaded() && timeWaited < 10) {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				logger.error(e);
			}
			++timeWaited;
		}
		if (timeWaited > 0) {
			logger.debug(String.format("Waiting %s milliseconds for page to refresh", (timeWaited * 250)));
		} else {
			logger.warn("Did not have to wait for page to refresh");
		}
	}

	/**
	 * Force wait
	 * 
	 * @param seconds
	 */
	public void waitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000); //
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set Value of an Input Field using Javascript. Takes input of css Selector of
	 * the element and the value to be set in the input field
	 * 
	 * @param cssSelector
	 * @param value
	 */
	public void setValueOfAnInputField(String cssSelector, String value) {
		String command = ("$(\"" + cssSelector + "\").val('" + value + "')");
		logger.info(String.format("Executing command - %s", command));
		executeScript(command);
		// need to some times executeChangeEventOnElement after updating a field
	}

	public boolean isElementExists(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			highlightElement(driver, locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Highlight function to apply a yellow background color to the element *
	 * 
	 * @param driver
	 * @param element
	 */
	public void highlightElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 3px solid red;');", element);

		// Wait for a brief moment to see the highlighted element
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Reset the element's original style
		js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
	}

	/**
	 * Highlight function to apply a yellow background color to the locator
	 * 
	 * @param driver
	 * @param element*
	 */
	public void highlightElement(WebDriver driver, By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String originalStyle = element.getAttribute("style");
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

		// Wait for a brief moment to see the highlighted element
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Reset the element's original style
		js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", element);
	}

}
