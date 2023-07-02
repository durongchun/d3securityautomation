package com.holinova.pageobject.page.roadtest;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.holinova.basepage.BasePage;
import com.holinova.pageobject.data.RoadTestData;
import com.holinova.pageobject.locator.RoadTestLocator;

import bsh.This;

public class RoatTestPage extends BasePage {
	public RoatTestPage(WebDriver driver) {
		super(driver);
	}

	private static final Logger log = LoggerFactory.getLogger(This.class);

	public void enterPage(RoadTestData roadData) {
		log.info("Go RoadTest Main Page");
		super.enterPage(roadData.getUrl());
	}

	public void clickSmsOption() {
		wait.until(ExpectedConditions.elementToBeClickable(RoadTestLocator.SNS_REDIOBOX));
		driver.findElement(RoadTestLocator.SNS_REDIOBOX).click();
	}

	public void signIn(RoadTestData roadData) {
		this.sendInput(RoadTestLocator.DRIVER_LN, roadData.getDriverLastName());
		this.sendInput(RoadTestLocator.DRIVER_LICENCENUMBER, roadData.getDriverLicenceNumber());
		this.sendInput(RoadTestLocator.KEYWORD, roadData.getKeyWord());
		driver.findElement(RoadTestLocator.TC_CHECKBOX).click();
		clickButton(RoadTestLocator.SIGN_IN);
	}

	public void inputLocation(RoadTestData roadData) throws InterruptedException {
		while (true) {
			this.sendInput(RoadTestLocator.LOCATION, roadData.getLocation()).sendKeys(Keys.ENTER);

			driver.findElement(RoadTestLocator.LOCATION).sendKeys(Keys.ENTER);

			if (driver.findElement(RoadTestLocator.SEARCH_BUTTON).isEnabled()) {
				clickButton(RoadTestLocator.SEARCH_BUTTON);
				if (isLangleyWillowDisplayed()) {
					break;
				} else {
					scrollElementTopToTop(RoadTestLocator.LOCATION);
				}

			}
		}

	}
	
	public boolean isLangleyWillowDisplayed() {
		try {
			return driver.findElement(RoadTestLocator.LANGLEY_WILLOW).isDisplayed();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
		
	}
	
	public boolean isErroTextDisplayed() {
		try {
			return driver.findElement(RoadTestLocator.ERR_TEXT).isDisplayed();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
		
	}

	public WebElement allTime() {
		return driver.findElement(RoadTestLocator.ALLTIME);
	}

	public void findLanyleyWillow() {
		wait.until(ExpectedConditions.elementToBeClickable(RoadTestLocator.LANGLEY_WILLOW));
		moveToElement(RoadTestLocator.LANGLEY_WILLOW);
		driver.findElement(RoadTestLocator.LANGLEY_WILLOW).click();
		wait.until(ExpectedConditions.elementToBeClickable(RoadTestLocator.WILLBROOK_DIALOG));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Boolean findOctDate() {
		List<WebElement> datesTitles = driver.findElements(RoadTestLocator.DATE_TITLE);
		for (WebElement dateTitle : datesTitles) {
			if (dateTitle.getText().contains("October")) {

				while (true) {
					driver.findElement(RoadTestLocator.TIME).click();
					if (driver.findElement(RoadTestLocator.REVIEW_APPOINTMENT).isEnabled()) {
						break;
					}
				}
				return true;
			}

		}

		return false;

	}

	public boolean isReschedule() throws Exception {
		try {
			if (driver.findElement(RoadTestLocator.RESCHEDULE).isDisplayed()) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}

	public void clickRescheduleAppointment() {
		driver.findElement(RoadTestLocator.RESCHEDULE).click();
		driver.findElement(RoadTestLocator.YES).click();
	}
}
