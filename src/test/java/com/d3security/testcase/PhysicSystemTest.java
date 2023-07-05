package com.d3security.testcase;

import org.testng.annotations.Test;

import com.alibaba.excel.util.Validate;
import com.d3security.basetest.BaseTest;
import com.d3security.basepage.*;
import com.d3security.pageobject.data.PhysicAddCaseData;
import com.d3security.pageobject.data.PhysicSystemData;
import com.d3security.pageobject.locator.PhysicSystemLocator;
import com.d3security.pageobject.page.LoginPage;
import com.d3security.pageobject.page.SiteConfiguration;
import com.d3security.pageobject.page.physicsystem.AddIncidentReportPage;
import com.d3security.pageobject.page.physicsystem.IncidentReportsPage;
import com.d3security.pageobject.page.physicsystem.DashboardPage;
import com.d3security.pageobject.page.physicsystem.HeaderPage;
import com.d3security.util.dataprovider.ExcelDataProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhysicSystemTest extends BaseTest {

	@Test(dataProviderClass = ExcelDataProvider.class, dataProvider = "PhysicData", description = "Physic Login", priority = 1)
	public void testPhysicFlow(Object[] dataObject) {
		PhysicSystemData physicSystemData = new PhysicSystemData();
		physicSystemData = (PhysicSystemData) dataObject[0];
		
		BaseBrowser baseBroser = new BaseBrowser(driver);
		PhysicSystemLocator physicSystemLocator = new PhysicSystemLocator();
		new SiteConfiguration(driver).enterPage(physicSystemLocator.url);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.signInPhysic(physicSystemData.getUserName(), physicSystemData.getPassWord());	
		//loginPage.LaunchVSOCManually();
		
		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.isDashboardExistingAndDisplayed();
		
		HeaderPage header = new HeaderPage(driver);	
		header.clickAddNewIcon();
		header.selectOptionFromAddNewDropdown(physicSystemData.getDropDownOption());
		
		//Create a new IncidentReport
		AddIncidentReportPage addIncidentReportPage = new AddIncidentReportPage(driver);
		addIncidentReportPage.newIncidentReportExistingAndDisplayed();
		
		addIncidentReportPage.clickAccidentDetails();
		addIncidentReportPage.selectTypeOfAccident(physicSystemData.getTypeofAccident());
		addIncidentReportPage.clickInvolvedClown();
		addIncidentReportPage.inputRequiredField(physicSystemData.getRequiredField());
		addIncidentReportPage.clickMandatoryFields();		
		addIncidentReportPage.inputMandatoryFields(physicSystemData.getMandatoryonSave(), physicSystemData.getMandatoryonClose());
		addIncidentReportPage.clickSave();		
		Validate.isTrue(addIncidentReportPage.isIncidentReportCreatedSuccessfully(), "IncidentReport created successfully");		
		
		final String incidentReportNo = addIncidentReportPage.getIncidentReportNumber();		
		IncidentReportsPage incidentReport = new IncidentReportsPage(driver);		
		incidentReport.goD3vSOCWindow();
		header.clickHamburgerMenu();
		header.clickIncidentReportsMenu();
		
		incidentReport.searchIR(incidentReportNo);
		Validate.isTrue(incidentReport.isDisplayingIR(incidentReportNo), "IncidentReport number is displayed in Grid");
 
		driver.manage().deleteAllCookies();
	}
	
	@Test(dataProviderClass = ExcelDataProvider.class, dataProvider = "PhysicCaseData", description = "Physic Add Case", priority = 1)
	public void testPhysicAddCase(Object[] dataObject) {
		PhysicAddCaseData physicAddCaseData = new PhysicAddCaseData();
		physicAddCaseData = (PhysicAddCaseData) dataObject[0];
		
		BaseBrowser baseBroser = new BaseBrowser(driver);
		PhysicSystemLocator physicSystemLocator = new PhysicSystemLocator();
		new SiteConfiguration(driver).enterPage(physicSystemLocator.url);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.signInPhysic(physicAddCaseData.getUserName(), physicAddCaseData.getPassWord());	
		//loginPage.LaunchVSOCManually();
		
		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.isDashboardExistingAndDisplayed();
		
		HeaderPage header = new HeaderPage(driver);	
		header.clickAddNewIcon();
		header.selectOptionFromAddNewDropdown(physicAddCaseData.getDropDownOption());
		
		//Create a new case
		
		
		
		driver.manage().deleteAllCookies();
	}
	
	


}
