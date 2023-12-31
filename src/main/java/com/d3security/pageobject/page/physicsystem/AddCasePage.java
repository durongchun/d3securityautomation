package com.d3security.pageobject.page.physicsystem;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.d3security.basepage.BasePage;
import com.d3security.pageobject.locator.PhysicSystemLocator;
import com.d3security.util.StepInfo;

public class AddCasePage extends BasePage {

	public AddCasePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void clickMandatoryField() {
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		clickElement(PhysicSystemLocator.MandatoryFields);
		waitForSeconds(1);
	}

	public void ClickCreateButt() {
		waitForSeconds(1);
		clickElement(PhysicSystemLocator.CreateButt);
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(2);

	}
	
	public void ClickPreview() {
		waitForSeconds(2);
		clickElement(PhysicSystemLocator.Preview);
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);

	}

	public void selectOwnerSite(String site) {
		StepInfo.addMessage("Select dropdown " + site);
		driver.switchTo().defaultContent();
		clickElement(PhysicSystemLocator.OkButtOnAlert);
		switchFrame(PhysicSystemLocator.IframeID);
		clickElement(PhysicSystemLocator.OwnerSiteBox);		
		List<WebElement> elements = driver.findElements(PhysicSystemLocator.OwnerSiteDropdown);
		for (WebElement ele : elements) {
			if (ele.getText().equals(site)) {
				waitForSeconds(6);				
				mouseOverToElement(ele);
				click(ele);
				waitForJQueryToLoad();
				waitForPageToRefresh();
				isPageLoaded();
				waitForSeconds(2);
				break;
			}
		}

	}

	public boolean isCaseCreatedSuccessfully(String caseNumber) {
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(2);				
		return isElementExists(driver, By.xpath(String.format(PhysicSystemLocator.IRShowInGrid, caseNumber)) );
	}
	
	public void searchCase(String input) {
		StepInfo.addMessage("Search Case");
		sendInput(PhysicSystemLocator.CaseSearchBox, input);
		driver.findElement(PhysicSystemLocator.CaseSearchBox).sendKeys(Keys.ENTER);
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
	}

	public String getCaseNumber() {
		ClickPreview();
		StepInfo.addMessage("Get Case number: " + driver.findElement(PhysicSystemLocator.CaseNumber).getText());
		highlightElement(driver, PhysicSystemLocator.CaseNumber);
		return driver.findElement(PhysicSystemLocator.CaseNumber).getText();
	}

}
