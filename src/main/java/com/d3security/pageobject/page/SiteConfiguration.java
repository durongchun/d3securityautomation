package com.d3security.pageobject.page;

import org.openqa.selenium.WebDriver;

import com.d3security.basepage.BasePage;
import com.d3security.util.StepInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SiteConfiguration extends BasePage {

	public SiteConfiguration(WebDriver driver) {
		super(driver);
	}

	public void enterPage(String url) {
		StepInfo.addMessage("Open Website URL:" + url);
		super.enterPage(url);
	}
	
	


}
