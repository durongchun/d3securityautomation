package com.d3security.pageobject.page.physicsystem;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.d3security.basepage.BasePage;
import com.d3security.pageobject.data.incidentreport.IRGeneralInformationData;
import com.d3security.pageobject.locator.PhysicSystemLocator;
import com.d3security.util.StepInfo;

public class AddIncidentReportPage extends BasePage {

	public AddIncidentReportPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub

	}

	public void clikIncidentType() {
		clickElement(PhysicSystemLocator.IncidentType);
	}

	public void clickAccidentDetails() {
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		clickElement(PhysicSystemLocator.AccidentDetails);
		waitForSeconds(1);
	}

	public void clickInvolvedClown() {
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		driver.switchTo().defaultContent();
		clickElement(PhysicSystemLocator.InvolvedClown);
		waitForSeconds(1);
	}

	public void clickMandatoryFields() {
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		driver.switchTo().defaultContent();
		clickElement(PhysicSystemLocator.MandatoryFields);
		waitForSeconds(1);
	}

	public void clickArrestDetails() {
		StepInfo.addMessage("Click Arrest Details");
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		clickElement(PhysicSystemLocator.ArrestDetails);
		waitForSeconds(1);
	}

	public void clickWorkplaceViolence() {
		StepInfo.addMessage("Click Workplace Violence");
		driver.switchTo().defaultContent();
		clickElement(PhysicSystemLocator.WorkplaceViolence);
		waitForSeconds(1);
	}

	public void clickSave() {
		StepInfo.addMessage("Save");
		driver.switchTo().defaultContent();
		clickElement(PhysicSystemLocator.SaveButt);
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(2);
	}

	public void clickNoOptionFromArrestDetails() {
		StepInfo.addMessage("Choose No");
		waitForSeconds(2);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		clickElement(PhysicSystemLocator.NoCheckboxForArrestDetails);
	}

	public void selectSection(String str1, String str2) {
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		clickElement(By.xpath(String.format(str1, str2)));
		waitForSeconds(1);

	}

	public void selectCheckbox() {
		clickElement(PhysicSystemLocator.CheckBox1);
		clickElement(PhysicSystemLocator.CheckBox2);
		clickElement(PhysicSystemLocator.CheckBox3);
		clickElement(PhysicSystemLocator.CheckBox3);

	}
	
	public void selectRadioBox(String radio) {
		clickElement(By.xpath(String.format(PhysicSystemLocator.RadioBoxOption, radio)));
	}
	
	public void selectRevealCheckbox() {
		clickElement(PhysicSystemLocator.RevealCheckBoxOption1);
		clickElement(PhysicSystemLocator.RevealCheckBoxOption2);
		clickElement(PhysicSystemLocator.RevealCheckBox2Option1);
		clickElement(PhysicSystemLocator.RevealCheckBox2Option2);
		clickElement(PhysicSystemLocator.RevealCheckBox2Option3);
		clickElement(PhysicSystemLocator.RevealCheckBox2Option4);
		clickElement(PhysicSystemLocator.RevealCheckBox2Option5);
		clickElement(PhysicSystemLocator.RevealCheckBox2Option6);
	}
	
	public void selectAddressDropdown(String city, String state, String country) {
		selectDropdownByVisibleText(PhysicSystemLocator.StateProvinceDropdown, city);
		selectDropdownByVisibleText(PhysicSystemLocator.StateDropdown, state);
		selectDropdownByVisibleText(PhysicSystemLocator.CountryDropdown, country);
	}

	public void checkNotifyRecipientOnce() {
		clickElement(PhysicSystemLocator.NotifyRecipientOnce);
	}

	public void checkEmailNotifyAssignRules() {
		clickElement(PhysicSystemLocator.UseEmailNotiAssignRules);
	}

	public void checkNotifyCCRecipients() {
		clickElement(PhysicSystemLocator.NotifyCCRecipients);
	}

	public void checkNotifyCreatorOnIRCreateandEdit() {
		clickElement(PhysicSystemLocator.Creator_OnIRCreateandEdit);
	}

	public void checkNotifyCreatorOnIRClose() {
		clickElement(PhysicSystemLocator.Creator_OnIRClose);
	}

	public void checkNotifyCreatorOnAssigneeReassign() {
		clickElement(PhysicSystemLocator.Creator_OnAssigneeReassign);
	}

	public void checkNotifyAssigneeOnIRCreateandEdit() {
		clickElement(PhysicSystemLocator.Assignee_OnIRCreateandEdit);
	}

	public void checkNotifyAssigneeOnIRClose() {
		clickElement(PhysicSystemLocator.Assignee_OnIRClose);
	}

	public void checkNotifyAssigneeOnReassign() {
		clickElement(PhysicSystemLocator.Assignee_OnAssigneeReassign);
	}

	public void inputRequiredField(String text) {
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		sendInput(PhysicSystemLocator.RequiredField, text);
		waitForSeconds(1);
	}

	public void inputMandatoryFields(String save, String close) {
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(1);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		sendInput(PhysicSystemLocator.MandatoryOnSave, save);
		// sendInput(PhysicSystemLocator.MandatoryOnClose, close);
		waitForSeconds(1);
	}

	public void inputTitle(String title) {
		waitForSeconds(2);
		sendInput(PhysicSystemLocator.TitleInput, title);
	}

	public void inputOneTimeEmailNotifyOnSave(String email) {
		sendInput(PhysicSystemLocator.OneTimeEmailOnSave, email);
	}

	public void addCCRecipients(String user, String group) {
		clickElement(PhysicSystemLocator.AddRecipientsLink);
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(5);
		driver.switchTo().defaultContent();
		try {
			switchHandleByTitle("Add recipient");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(By.xpath(String.format(PhysicSystemLocator.AddRecipients, user))).click();
		clickElement(PhysicSystemLocator.AddRecipientsUserSign);
		driver.findElement(By.xpath(String.format(PhysicSystemLocator.AddRecipients, group))).click();
		clickElement(PhysicSystemLocator.AddRecipientsGroupSign);
		clickElement(PhysicSystemLocator.AddRecipientsDoneButt);

	}

	public void selectOccurredOn(String date) {
		clickElement(PhysicSystemLocator.IncidentOccuredOnDate);
		mouseOverToElement(driver.findElement(By.xpath(String.format(PhysicSystemLocator.SelectDay, date))));
		clickElement(By.xpath(String.format(PhysicSystemLocator.SelectDay, date)));

	}

	public void selectEndedOn(String date) {
		clickElement(PhysicSystemLocator.IncidentEndedONDate);
		mouseOverToElement(driver.findElement(By.xpath(String.format(PhysicSystemLocator.SelectDay, date))));
		clickElement(By.xpath(String.format(PhysicSystemLocator.SelectDay, date)));

	}

	public void selectIncidentType(String type) {
		waitForSeconds(2);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		clikIncidentType();
		sendInput(PhysicSystemLocator.IncidentTypeInput, type);
		driver.findElement(PhysicSystemLocator.IncidentTypeInput).sendKeys(Keys.ENTER);
		waitForJQueryToLoad();
		waitForPageToRefresh();
	}

	public void selectTypeOfAccident(String type) {
		waitForSeconds(2);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		if (type.contains("Posture")) {
			clickElement(PhysicSystemLocator.PostureRadioBox);
		}
	}

	public void selectOptionFromWorkplaceViolence(String value) {
		StepInfo.addMessage("Choose " + value);
		waitForSeconds(2);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		selectDropdownByVisibleText(PhysicSystemLocator.WorkplaceViolenceOption, value);
	}

	public void selectDropdownMenu(String menu) {
		StepInfo.addMessage("Choose " + menu);
		waitForSeconds(2);
		selectDropdownByVisibleText(PhysicSystemLocator.DropdownMenu, menu);

	}
	
	public void selectListBoxAllOptions() {
		selectDropdownAllOptions(PhysicSystemLocator.ListBoxOptions);
	}

	public boolean newIncidentReportExistingAndDisplayed() {
		StepInfo.addMessage("Add IncidentReport Window is opening");
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(5);
		driver.switchTo().defaultContent();
		try {
			switchHandleByTitle("Incident Report");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isElementExists(driver, PhysicSystemLocator.NewIncidentReportTitle);

	}

	public void goIncidentReportWindow() {
		waitForSeconds(2);
		// All windows
		Set<String> allHandlesSet = driver.getWindowHandles();
		// Find the window title
		for (String handle : allHandlesSet) {
			driver.switchTo().window(handle);
			if (driver.getTitle().contains("Incident Report")) {
				break;
			}
		}
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
	}

	public boolean isIncidentReportCreatedSuccessfully() {
		waitForJQueryToLoad();
		waitForPageToRefresh();
		waitForSeconds(6);
		switchFrame(PhysicSystemLocator.IncidentReportIframe);
		waitForPageToRefresh();
		waitForSeconds(6);
		return isElementExists(driver, PhysicSystemLocator.IncidentNumber);
	}

	public String getIncidentReportNumber() {
		StepInfo.addMessage(
				"Get IncidentReport number: " + driver.findElement(PhysicSystemLocator.IncidentNumber).getText());
		return driver.findElement(PhysicSystemLocator.IncidentNumber).getText();
	}

}
