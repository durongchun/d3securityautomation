package com.d3security.pageobject.page;

import org.openqa.selenium.WebDriver;

import com.d3security.basepage.BasePage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SiteConfiguration extends BasePage {

	public SiteConfiguration(WebDriver driver) {
		super(driver);
	}

	public void enterPage(String url) {
		log.info("Go to Cyber System Login page");
		super.enterPage(url);
	}
	
	


}
