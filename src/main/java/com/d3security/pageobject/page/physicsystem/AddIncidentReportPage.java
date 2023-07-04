package com.d3security.pageobject.page.physicsystem;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.d3security.basepage.BasePage;
import com.d3security.pageobject.locator.PhysicSystemLocator;

public class AddIncidentReportPage extends BasePage {

	public AddIncidentReportPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub

	}

	public void clickArrestDetails() {
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		clickElement(PhysicSystemLocator.ArrestDetails);
		waitForSeconds(1);
	}

	public void clickWorkplaceViolence() {
		driver.switchTo().defaultContent();
		clickElement(PhysicSystemLocator.WorkplaceViolence);
		waitForSeconds(1);
	}

	public void clickSave() {
		driver.switchTo().defaultContent();
		clickElement(PhysicSystemLocator.SaveButt);
		waitForJQueryToLoad();
		waitForPageToRefresh();
	}

	public void clickNoOptionFromArrestDetails() {
		waitForSeconds(2);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		clickElement(PhysicSystemLocator.NoCheckboxForArrestDetails);
	}

	public void selectOptionFromWorkplaceViolence(String value) {
		waitForSeconds(2);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		selectDropdownByVisibleText(PhysicSystemLocator.WorkplaceViolenceOption, value);
	}

	public boolean newIncidentReportExistingAndDisplayed() {
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(5);
		driver.switchTo().defaultContent();
		try {
			switchHandleByTitle("Incident Report");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isElementExists(driver, PhysicSystemLocator.NewIncidentReportTitle);

	}

	public boolean isIncidentReportCreatedSuccessfully() {
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(6);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		waitForSeconds(6);
		return isElementExists(driver, PhysicSystemLocator.IncidentNumber);
	}

	public String getIncidentReportNumber() {
		return driver.findElement(PhysicSystemLocator.IncidentNumber).getText();
	}

}
