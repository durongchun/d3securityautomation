package com.d3security.pageobject.page.cybersystem;

import org.openqa.selenium.WebDriver;

import com.d3security.basepage.BasePage;
import com.d3security.pageobject.locator.CyberSystemLocator;

public class InvestigationDashboardPage extends BasePage {

	public InvestigationDashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean isInvestigaitonDashboardExistingAndDisplayed() {
		// this.switchFrame(CyberSystemLocator.IframeID);
		this.switchSecondWindow();
		driver.switchTo().frame("FrameOnLifeServer");
		return this.isElementExists(driver, CyberSystemLocator.InvestigationDashboard);

	}

}
