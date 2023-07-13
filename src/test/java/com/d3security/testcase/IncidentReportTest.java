package com.d3security.testcase;

import org.testng.annotations.Test;

import com.alibaba.excel.util.Validate;
import com.d3security.basepage.BaseBrowser;
import com.d3security.basetest.BaseTest;
import com.d3security.pageobject.data.PhysicAddCaseData;
import com.d3security.pageobject.data.PhysicSystemData;
import com.d3security.pageobject.data.incidentreport.IRGeneralInformationData;
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
public class IncidentReportTest extends BaseTest {

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
		
		addIncidentReportPage.selectIncidentType(generalInformationData.getIncidentType());
		addIncidentReportPage.inputTitle(generalInformationData.getTitle());
		addIncidentReportPage.addCCRecipients(generalInformationData.getCcUsers(), generalInformationData.getCcGroups());
		
		if (generalInformationData.getNotifyRecipientOnce().equals("TRUE")) {
			addIncidentReportPage.checkNotifyCCRecipients();
		}
		
		addIncidentReportPage.selectOccurredOn(generalInformationData.getIncidentOccurredOn());		
		addIncidentReportPage.selectOccurredOn(generalInformationData.getIncidentEndedOn());
		
		addIncidentReportPage.inputOneTimeEmailNotifyOnSave(generalInformationData.getSendOneTimeEmailNotificationOnSave());
		
		if (generalInformationData.getEmailNotificationAssignmentRules().equals("TRUE")) {
			addIncidentReportPage.checkEmailNotifyAssignRules();
		}
		
		if (generalInformationData.getNotifyCCRecipientsOnCreateEditandClose().equals("TRUE")) {
			addIncidentReportPage.checkNotifyCCRecipients();
		}
		
		if (generalInformationData.getCreator_onIRCreateandEdit().equals("TRUE")) {
			addIncidentReportPage.checkNotifyCreatorOnIRCreateandEdit();
		}
		
		if (generalInformationData.getCreator_onIRClose().equals("TRUE")) {
			addIncidentReportPage.checkNotifyCreatorOnIRClose();
		}
		
		if (generalInformationData.getCreator_onAssigneeReassign().equals("TRUE")) {
			addIncidentReportPage.checkNotifyCreatorOnAssigneeReassign();
		}
		
		if (generalInformationData.getAssignee_onIRCreateandEdit().equals("TRUE")) {
			addIncidentReportPage.checkNotifyAssigneeOnIRCreateandEdit();
		}
		
		if (generalInformationData.getAssignee_onIRClose().equals("TRUE")) {
			addIncidentReportPage.checkNotifyAssigneeOnIRClose();
		}
		
		if (generalInformationData.getAssignee_onAssigneeReassign().equals("TRUE")) {
			addIncidentReportPage.checkNotifyAssigneeOnReassign();
		}
		
		addIncidentReportPage.clickAccidentDetails();
		addIncidentReportPage.selectTypeOfAccident(generalInformationData.getTypeofAccident());
		addIncidentReportPage.clickInvolvedClown();
		addIncidentReportPage.inputRequiredField(generalInformationData.getRequiredField());
		addIncidentReportPage.clickMandatoryFields();
		addIncidentReportPage.inputMandatoryFields(generalInformationData.getMandatoryonSave(),
				generalInformationData.getMandatoryonClose());
		addIncidentReportPage.clickSave();
		Verify.verifyTrue(addIncidentReportPage.isIncidentReportCreatedSuccessfully(),
				String.format("%s", "<b>IncidentReport created successfully</b>"), driver);

		final String incidentReportNo = addIncidentReportPage.getIncidentReportNumber();
		IncidentReportsPage incidentReport = new IncidentReportsPage(driver);
		incidentReport.goD3vSOCWindow();
		header.clickHamburgerMenu();
		header.clickIncidentReportsMenu();
		incidentReport.searchIR(incidentReportNo);
		Verify.verifyTrue(incidentReport.isDisplayingIR(incidentReportNo),
				String.format("%s", "<b>IncidentReport number is displayed in Grid</b>"), driver);

		driver.manage().deleteAllCookies();

	}
}
