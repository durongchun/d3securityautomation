package com.d3security.testcase;

import org.testng.annotations.Test;

import com.alibaba.excel.util.Validate;
import com.d3security.basepage.BaseBrowser;
import com.d3security.basetest.BaseTest;
import com.d3security.pageobject.data.PhysicAddCaseData;
import com.d3security.pageobject.data.PhysicSystemData;
import com.d3security.pageobject.data.incidentreport.IRAllElementsData;
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

	@Test(dataProviderClass = ExcelDataProvider.class, dataProvider = "PhysicIRGeneralInformationData", description = "Physic IR GeneralInformation Test", priority = 1)
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
		addIncidentReportPage.addCCRecipients(generalInformationData.getCcUsers(),
				generalInformationData.getCcGroups());

		if (generalInformationData.getNotifyRecipientOnce().toLowerCase().equals("true")) {
			addIncidentReportPage.goIncidentReportWindow();
			addIncidentReportPage.checkNotifyRecipientOnce();
		}

		addIncidentReportPage.selectOccurredOn(generalInformationData.getIncidentOccurredOn());
		addIncidentReportPage.selectEndedOn(generalInformationData.getIncidentEndedOn());

		addIncidentReportPage
				.inputOneTimeEmailNotifyOnSave(generalInformationData.getSendOneTimeEmailNotificationOnSave());

		if (generalInformationData.getEmailNotificationAssignmentRules().toLowerCase().equals("true")) {
			addIncidentReportPage.checkEmailNotifyAssignRules();
		}

		if (generalInformationData.getNotifyCCRecipientsOnCreateEditandClose().toLowerCase().equals("true")) {
			addIncidentReportPage.checkNotifyCCRecipients();
		}

		if (generalInformationData.getCreator_onIRCreateandEdit().toLowerCase().equals("true")) {
			addIncidentReportPage.checkNotifyCreatorOnIRCreateandEdit();
		}

		if (generalInformationData.getCreator_onIRClose().toLowerCase().equals("true")) {
			addIncidentReportPage.checkNotifyCreatorOnIRClose();
		}

		if (generalInformationData.getCreator_onAssigneeReassign().toLowerCase().equals("true")) {
			addIncidentReportPage.checkNotifyCreatorOnAssigneeReassign();
		}

		if (generalInformationData.getAssignee_onIRCreateandEdit().toLowerCase().equals("true")) {
			addIncidentReportPage.checkNotifyAssigneeOnIRCreateandEdit();
		}

		if (generalInformationData.getAssignee_onIRClose().toLowerCase().equals("true")) {
			addIncidentReportPage.checkNotifyAssigneeOnIRClose();
		}

		if (generalInformationData.getAssignee_onAssigneeReassign().toLowerCase().equals("true")) {
			addIncidentReportPage.checkNotifyAssigneeOnReassign();
		}

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

	@Test(dataProviderClass = ExcelDataProvider.class, dataProvider = "PhysicIRAllElementsData", description = "Physic IR All Elements Test", priority = 1)
	public void testPhysicAllElements(Object[] dataObject) {
		IRAllElementsData allElementsData = new IRAllElementsData();
		allElementsData = (IRAllElementsData) dataObject[0];

		PhysicSystemLocator physicSystemLocator = new PhysicSystemLocator();
		new SiteConfiguration(driver).enterPage(physicSystemLocator.url);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.signInPhysic(allElementsData.getUserName(), allElementsData.getPassWord());
		// loginPage.LaunchVSOCManually();

		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.isDashboardExistingAndDisplayed();

		HeaderPage header = new HeaderPage(driver);
		header.clickAddNewIcon();
		header.selectOptionFromAddNewDropdown(allElementsData.getDropDownOption());

		// Create a new IncidentReport
		StepInfo.addMessage("Create IR");
		AddIncidentReportPage addIncidentReportPage = new AddIncidentReportPage(driver);
		addIncidentReportPage.newIncidentReportExistingAndDisplayed();

		addIncidentReportPage.selectIncidentType(allElementsData.getIncidentType());

		addIncidentReportPage.selectSection(physicSystemLocator.Section, allElementsData.getSection());

		if (allElementsData.getCheckBox().toLowerCase().equals("true")) {
			addIncidentReportPage.selectCheckbox();
		}

		addIncidentReportPage.selectDropdownMenu(allElementsData.getDropdownMenu());

		if (allElementsData.getListBox().toLowerCase().equals("true")) {
			addIncidentReportPage.selectListBoxAllOptions();
		}

		addIncidentReportPage.selectRadioBox(allElementsData.getRadioBox());

		if (allElementsData.getRevealCheckBox1_1().toLowerCase().equals("true")
				&& allElementsData.getRevealCheckBox1_2().toLowerCase().equals("true")) {
			addIncidentReportPage.selectRevealCheckbox();
		}

		addIncidentReportPage.selectRadioBox(allElementsData.getRadioBoxLevel());

		addIncidentReportPage.selectAddressDropdown(allElementsData.getStateDropDown(),
				allElementsData.getProvinceDropdown(), allElementsData.getCountryDropdown());

		addIncidentReportPage.selectTimeControl(allElementsData.getTimeControlHour(),
				allElementsData.getTimeControlMin(), allElementsData.getNoonPicker());

		addIncidentReportPage.selectCanlendarAndTimeControl(allElementsData.getCalendarControl(),
				allElementsData.getTimeControlHour(), allElementsData.getTimeControlMin());

		addIncidentReportPage.selectCanlendarControl(allElementsData.getCalendarControl());

		addIncidentReportPage.selectCurrencyTextBox(allElementsData.getCurrencyTextBox(),
				allElementsData.getCurrencyDropdown());

		if (allElementsData.getSumUpAmount().toLowerCase().equals("true")) {
			Verify.verifyTrue(
					addIncidentReportPage.isCaculatedTotalAmountDisplaying(allElementsData.getCurrencyDropdown(),
							allElementsData.getCurrencyTextBox()),
					"Calculated Total Amount is displayed as  " + allElementsData.getCurrencyTextBox(), driver);

		}

		if (allElementsData.getSearchableTextBox().toLowerCase().equals("true")
				&& allElementsData.getSearchableTextArea().toLowerCase().equals("true")) {
			addIncidentReportPage.selectSearchableTextBox();
			Verify.verifyTrue(addIncidentReportPage.isAutofilledTextDisplayedTextBoxArea2(),
					"Searchable TextBox and area are auto-filled", driver);
		}
		
		addIncidentReportPage.inputTextArea(allElementsData.getTextArea());
		addIncidentReportPage.inputTextBox(allElementsData.getTextBox());
		
		

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
