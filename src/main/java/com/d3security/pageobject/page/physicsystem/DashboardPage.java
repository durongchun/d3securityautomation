package com.d3security.pageobject.page.physicsystem;

import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.d3security.basepage.BasePage;
import com.d3security.pageobject.locator.CyberSystemLocator;
import com.d3security.pageobject.locator.PhysicSystemLocator;
import com.d3security.util.StepInfo;

public class DashboardPage extends BasePage {

	public DashboardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean isDashboardExistingAndDisplayed() {
		this.switchSecondWindow();
		this.switchFrame(PhysicSystemLocator.IframeID);
		// driver.switchTo().frame("FrameOnLifeServer");
		StepInfo.addMessage("Dashboard is displaying");
		return this.isElementExists(driver, PhysicSystemLocator.Dashboard);

	}
	
	
	
	

}
