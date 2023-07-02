package com.holinova.pageobject.locator;

import org.openqa.selenium.By;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class ErpSystemLocator {
	 
	 //-----BookDemo Page----
	public static final By SEARCH_INPUT = By.xpath("//input[@id='kw']");
	public static final  By BUISNESSEMAIL_INPUT = By.id("email");
	public static final  By FIRSTNAME_INPUT = By.id("first_name");
	public static final  By LASTNAME_INPUT = By.id("last_name");
	public static final  By PHONENUMBER_INPUT = By.id("phone_number");
	public static final  By COMPANY_INPUT = By.id("company");
	public static final  By BOOKDEMO_BUTTON = By.cssSelector("[type='submit']");
	 
	//----Layout Guest page---
	 public static final By LAYOUT_PAGE = By.className("layout-guest");
}
