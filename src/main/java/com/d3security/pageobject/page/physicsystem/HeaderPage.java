package com.d3security.pageobject.page.physicsystem;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.d3security.basepage.BasePage;
import com.d3security.pageobject.locator.PhysicSystemLocator;

public class HeaderPage extends BasePage {

	public HeaderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void clickHamburgerMenu() {	
		logger.info("Click Hamburger Menu");
		waitForSeconds(1);	
		switchFrame(PhysicSystemLocator.IframeID);
		mouseOverToElement(driver.findElement(PhysicSystemLocator.HamburgerMenu));		
		clickElementWithJavaScript(driver, driver.findElement(PhysicSystemLocator.HamburgerMenu));
	}

	public void clickIncidentReportsMenu() {
		logger.info("Click IncidentReports Menu");
		waitForSeconds(1);	
		clickElementWithJavaScript(driver,driver.findElement(PhysicSystemLocator.IncidentReportsMenu));
	}

	public void clickAddNewIcon() {		
		logger.info("Click Add New Icon");
		waitForSeconds(1);		
		mouseOverToElement(driver.findElement(PhysicSystemLocator.CreateIcon));
		waitForSeconds(1);
	}

	public void selectOptionFromAddNewDropdown(String str) {
		logger.info("Select dropdown " + str);
		List<WebElement> elements = driver.findElements(PhysicSystemLocator.AddNewDropdownOptions);		
		for (WebElement ele : elements) {
			if (ele.getText().equals(str)) {
				//waitForSeconds(3);	
				mouseOverToElement(ele);
				click(ele);
				isPageLoaded();
				
			}
		}
	}

}
