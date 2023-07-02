package com.d3security.basetest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.d3security.pageobject.page.LoginPage;
import com.d3security.util.PropertiesReader;
import com.d3security.util.RedisUtil;
import com.d3security.util.WordartDisplayer;

import lombok.extern.slf4j.Slf4j;

/**
 * Test base class
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20
 */
@Slf4j
public class BaseTest {
	/**
	 * Redis
	 */
	public RedisUtil redisUtil;

//	public MybatisUtil mybatisUtil;

	/**
	 * Base driver
	 */
	private BaseDriver baseDriver;

	/**
	 * Driver
	 */
	public WebDriver driver;

	/**
	 * Execute before test suite and read the configuration file without multiple
	 * threads
	 *
	 * @param propertiesPath project configuration file path
	 * @throws IOException IOException
	 */
	@BeforeSuite(alwaysRun = true)
	@Parameters({ "propertiesPath" })
	public void beforeSuite(@Optional("src/test/resources/config/config.properties") String propertiesPath)
			throws IOException {
		// Display text webuitest4j
		WordartDisplayer.display();
		// Read configuration file
		PropertiesReader.readProperties(propertiesPath);
		// Redis
		RedisUtil.initJedisPool();
		// todo : customize the initialization operation of other tools if needed
	}

	/**
	 * Execute before executing test case with multi-thread
	 *
	 * @param browserName
	 * @param terminal       pc/h5（default by pc）
	 * @param deviceName     default by desktop
	 * @param remoteIP
	 * @param remotePort     default by 4444
	 * @param browserVersion
	 */
	@BeforeTest(alwaysRun = true)
	@Parameters({ "browserName", "terminal", "deviceName", "remoteIP", "remotePort", "browserVersion" })
	public void beforeTest(@Optional("chrome") String browserName, @Optional("pc") String terminal,
			@Optional("desktop") String deviceName, @Optional() String remoteIP, @Optional("4444") int remotePort,
			@Optional() String browserVersion) {
		/* Connect redis */
		redisUtil = new RedisUtil();
		redisUtil.initJedis();
		/* Driver configuration */
		baseDriver = new BaseDriver();
		driver = baseDriver.startBrowser(browserName, terminal, deviceName, remoteIP, remotePort, browserVersion);

	}

	/**
	 * Execute before executing a class
	 */
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		// Login here
		LoginPage loginPage = new LoginPage(driver, redisUtil);
		loginPage.enterPage();
		loginPage.loginByAPI();

	}

	/**
	 * Execute after executing a class
	 */
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		// todo : log off or other action
	}

	/**
	 * Execute after executing a test case
	 *
	 * @throws InterruptedException sleep
	 */
	@AfterTest(alwaysRun = true)
	public void afterTest() throws InterruptedException {
		// Close browser
		baseDriver.closeBrowser();
		driver = null;
		// Return redis
		redisUtil.returnJedis();
	}

	/**
	 * Execute after test suite
	 */
	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		// todo : customize if needed
	}
}