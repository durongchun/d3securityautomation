package com.d3security.testcase;

import org.testng.annotations.Test;

import com.d3security.basetest.BaseTest;
import com.d3security.pageobject.data.CyberSystemData;
import com.d3security.pageobject.locator.CyberSystemLocator;
import com.d3security.pageobject.page.LoginPage;
import com.d3security.pageobject.page.SiteConfiguration;
import com.d3security.pageobject.page.cybersystem.InvestigationDashboardPage;
import com.d3security.util.dataprovider.ExcelDataProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CyberSystemTest extends BaseTest {

	@Test(dataProviderClass = ExcelDataProvider.class, dataProvider = "CyberData", description = "Cyber Login", priority = 1)
	public void testCyberFlow(Object[] dataObject) {
		CyberSystemData cyberSystemData = new CyberSystemData();
		cyberSystemData = (CyberSystemData) dataObject[0];

		CyberSystemLocator cyberSystemLocator = new CyberSystemLocator();
		new SiteConfiguration(driver).enterPage(cyberSystemLocator.url);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.signInCyber(cyberSystemData);
		loginPage.LaunchVSOCManually();

		InvestigationDashboardPage investigationDashboard = new InvestigationDashboardPage(driver);
		investigationDashboard.isInvestigaitonDashboardExistingAndDisplayed();

		driver.manage().deleteAllCookies();
	}

}
