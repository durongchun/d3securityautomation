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
	
	public void clickAccidentDetails() {		
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		clickElement(PhysicSystemLocator.AccidentDetails);
		waitForSeconds(1);
	}
	
	public void clickInvolvedClown() {		
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		driver.switchTo().defaultContent();
		clickElement(PhysicSystemLocator.InvolvedClown);
		waitForSeconds(1);
	}
	
	public void clickMandatoryFields() {		
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		driver.switchTo().defaultContent();
		clickElement(PhysicSystemLocator.MandatoryFields);
		waitForSeconds(1);
	}

	public void clickArrestDetails() {
		logger.info("Click Arrest Details");
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		clickElement(PhysicSystemLocator.ArrestDetails);
		waitForSeconds(1);
	}

	public void clickWorkplaceViolence() {
		logger.info("Click Workplace Violence");
		driver.switchTo().defaultContent();
		clickElement(PhysicSystemLocator.WorkplaceViolence);
		waitForSeconds(1);
	}

	public void clickSave() {
		logger.info("Save");
		driver.switchTo().defaultContent();
		clickElement(PhysicSystemLocator.SaveButt);
		waitForJQueryToLoad();
		waitForPageToRefresh();
	}

	public void clickNoOptionFromArrestDetails() {
		logger.info("Choose No");
		waitForSeconds(2);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		clickElement(PhysicSystemLocator.NoCheckboxForArrestDetails);
	}
	
	public void inputRequiredField(String text) {
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		sendInput(PhysicSystemLocator.RequiredField, text);
		waitForSeconds(1);
	}
	
	public void inputMandatoryFields(String save, String close) {
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		sendInput(PhysicSystemLocator.MandatoryOnSave, save);		
		//sendInput(PhysicSystemLocator.MandatoryOnClose, close);
		waitForSeconds(1);
	}
	
	public void selectTypeOfAccident(String type) {
		waitForSeconds(2);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		if (type.contains("Posture")) {
			clickElement(PhysicSystemLocator.PostureRadioBox);
		}
	}

	public void selectOptionFromWorkplaceViolence(String value) {
		logger.info("Choose " + value);
		waitForSeconds(2);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		selectDropdownByVisibleText(PhysicSystemLocator.WorkplaceViolenceOption, value);
	}

	public boolean newIncidentReportExistingAndDisplayed() {
		logger.info("Add IncidentReport Window is opening");
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
		logger.info("Get IncidentReport number: " + driver.findElement(PhysicSystemLocator.IncidentNumber).getText());
		return driver.findElement(PhysicSystemLocator.IncidentNumber).getText();
	}

}
