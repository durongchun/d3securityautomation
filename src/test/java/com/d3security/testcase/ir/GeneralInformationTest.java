package com.d3security.testcase.ir;

import org.testng.annotations.Test;

import com.alibaba.excel.util.Validate;
import com.d3security.basepage.BaseBrowser;
import com.d3security.basetest.BaseTest;
import com.d3security.pageobject.data.PhysicAddCaseData;
import com.d3security.pageobject.data.PhysicSystemData;
import com.d3security.pageobject.data.ir.IRGeneralInformationData;
import com.d3security.pageobject.locator.PhysicSystemLocator;
import com.d3security.pageobject.page.LoginPage;
import com.d3security.pageobject.page.SiteConfiguration;
import com.d3security.pageobject.page.physicsystem.AddCasePage;
import com.d3security.pageobject.page.physicsystem.AddIncidentReportPage;
import com.d3security.pageobject.page.physicsystem.DashboardPage;
import com.d3security.pageobject.page.physicsystem.HeaderPage;
import com.d3security.pageobject.page.physicsystem.IncidentReportsPage;
import com.d3security.util.StepInfo;
import com.d3security.util.Verify;
import com.d3security.util.dataprovider.ExcelDataProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneralInformationTest extends BaseTest {

	@Test(dataProviderClass = ExcelDataProvider.class, dataProvider = "PhysicIRData", description = "Physic IR GeneralInformation Test", priority = 1)
	public void testPhysicGeneralInformation(Object[] dataObject) {
		IRGeneralInformationData generalInformationData = new IRGeneralInformationData();
		generalInformationData = (IRGeneralInformationData) dataObject[0];

		PhysicSystemLocator physicSystemLocator = new PhysicSystemLocator();
		new SiteConfiguration(driver).enterPage(physicSystemLocator.url);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.signInPhysic(generalInformationData.getUserName(), generalInformationData.getPassWord());
		// loginPage.LaunchVSOCManually();

		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.isDashboardExistingAndDisplayed();

		HeaderPage header = new HeaderPage(driver);
		header.clickAddNewIcon();
		header.selectOptionFromAddNewDropdown(generalInformationData.getDropDownOption());

		// Create a new IncidentReport
		StepInfo.addMessage("Create IR");
		AddIncidentReportPage addIncidentReportPage = new AddIncidentReportPage(driver);
		addIncidentReportPage.newIncidentReportExistingAndDisplayed();

	}
}
