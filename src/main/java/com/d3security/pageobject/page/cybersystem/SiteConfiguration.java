package com.holinova.pageobject.page.erpsystem;

import org.openqa.selenium.WebDriver;

import com.holinova.basepage.BasePage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SiteConfiguration extends BasePage {

	public SiteConfiguration(WebDriver driver) {
		super(driver);
	}

	public void enterPage() {
		log.info("Go to ERP main page");
		super.enterPage("https://try.fishbowlinventory.com/erp/");
	}

}
