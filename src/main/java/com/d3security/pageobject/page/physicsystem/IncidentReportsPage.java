package com.d3security.pageobject.page.physicsystem;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.d3security.basepage.BasePage;
import com.d3security.pageobject.locator.PhysicSystemLocator;

public class IncidentReportsPage extends BasePage {

	public IncidentReportsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void goD3vSOCWindow() {
		logger.info("Go D3vSOC Window");
		driver.close();
		Set<String> allHandlesSet = driver.getWindowHandles();
		for (String handle : allHandlesSet) {
			driver.switchTo().window(handle);
			if (driver.getTitle().contains("D3 vSOC (V11.5.376.0)")) {
				break;
			}
		}

	}

	public void searchIR(String input) {
		logger.info("Search IR");
		sendInput(PhysicSystemLocator.SearchInput, input);
		driver.findElement(PhysicSystemLocator.SearchInput).sendKeys(Keys.ENTER);
	}
	
	public boolean isDisplayingIR(String number) {		
		return isElementExists(driver, By.xpath(String.format(PhysicSystemLocator.IRShowInGrid, number)) );		
	}

}
