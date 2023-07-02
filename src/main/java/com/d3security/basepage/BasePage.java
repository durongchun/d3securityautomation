package com.d3security.basepage;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.d3security.util.MybatisUtil;
import com.d3security.util.RedisUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Encapsulate the operation methods that can be used by each module page
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20
 */
@Slf4j
@Getter
@Setter
public class BasePage extends BaseBrowser {
	/**
	 * Constructor 1
	 *
	 * @param driver drive
	 */
	

	public BasePage(WebDriver driver) {
		super(driver);	

	}

	/**
	 * Constructor 2
	 *
	 * @param driver    drive
	 * @param redisUtil redis connection tool
	 */
	public BasePage(WebDriver driver, RedisUtil redisUtil) {
		super(driver, redisUtil);
	}
	
	/* ============================== Actions that Can be Shared By the Page ============================== */

	/**
	 * Many WEB projects use some frameworks, and each module of the page is relatively similar, so a BasePage type is set up 
	 * here to store the common operation methods. 
	 * The difference between BaseBrowser and BasePage is that BasePage stores operations that can be shared by each module page, 
	 * and BaseBrowser only stores the most basic page operations.
	 * <p>
	 * Function1
	 */
	public void function1() {
		log.info("function1");
		// todo : common operation 1
	}

	/**
	 * Function2
	 *
	 * @return true
	 */
	public boolean function2() {
		// todo : common operation 2
		log.info("function2");
		return true;
	}

	/**
	 * Function3
	 *
	 * @return true
	 */
	public boolean function3() {
		// todo : common operation 3
		log.info("function3");
		return true;
	}

	/* ============================== Page Basic Assertion ============================== */

	/**
	 * If the current page title is the specified title
	 *
	 * @param title 
	 * @return boolean
	 */
	public boolean ifTitleIs(String title) {
		return wait.until(ExpectedConditions.titleIs(title));
	}

	/**
	 * If the current page title contains the specified text
	 *
	 * @param text 
	 * @return boolean
	 */
	public boolean ifTitleContains(String text) {
		return wait.until(ExpectedConditions.titleContains(text));
	}

	/**
	 * If the text value of an element on the current page is the specified text
	 *
	 * @param locator 
	 * @param text    
	 * @return boolean
	 */
	public boolean ifTextExists(By locator, String text) {
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}

	// todo : The public operations in the page can be encapsulated 
}
