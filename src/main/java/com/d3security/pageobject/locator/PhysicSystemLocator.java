package com.d3security.pageobject.locator;

import org.openqa.selenium.By;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PhysicSystemLocator {
	//-----Site----
	public final String url = "https://v2019.d3securityonline.net/V127Physical/VSOC/";
	
	//-----Login Page----
	public static final By UserName = By.id("username");
	public static final  By PassWord = By.id("password");
	public static final  By SubmitButt = By.id("Button_Login");
	
	//----Launch page---
	 public static final By LaunchVSOC = By.className("button-submit");
	 public static final By IframeID = By.id("FrameOnLifeServer");

	//----Launch page---	
	public static final By Dashboard = By.id("lbDashboardTitle"); 
	
	//----Header---	
	public static final By HamburgerMenu = By.xpath("//a[@id='hamburger']"); 
	public static final By IncidentReportsMenu = By.xpath("//a[contains(text(),'Incident Reports')]");	
	
	public static final By CreateIcon = By.id("createIcon"); 
	public static final By AddNewMenu = By.xpath("//span[@id='lbDashboardTitle']/../../..//*[@id='newMenuDiv']");
	public static final By AddNewDropdownOptions = By.xpath("//ul[contains(@class, 'k-reset k-state-border-up')]//li//a");
	
	//----Incident Report---	
	public static final By NewIncidentReportTitle = By.cssSelector("div.de-title");
	public static final By AccidentDetails = By.xpath("//span[contains(text(),'Accident Details')]");
	public static final By InvolvedClown = By.xpath("//span[contains(text(),'Involved Clown')]");
	public static final By MandatoryFields = By.xpath("//*[contains(text(),'_Mandatory Fields')]");
	public static final By PostureRadioBox = By.id("DFC2_dcElement821_2");
	public static final By RequiredField = By.id("DFC2_dcElement21801");
	public static final By MandatoryOnSave = By.id("DFC2_dcElement32329");
	public static final By MandatoryOnClose = By.id("DFC2_dcElement32330");
	public static final By ArrestDetails = By.xpath("//span[contains(text(),'Arrest Details')]");
	public static final By NoCheckboxForArrestDetails = By.id("DFC2_dcElement7031_1");
	public static final By WorkplaceViolence = By.xpath("//span[contains(text(),'Workplace Violence')]");
	public static final By WorkplaceViolenceOption = By.id("DFC2_dcElement6828");
	public static final By SaveButt = By.id("btnSave");
	public static final By IncidentNumber = By.id("irView_IncidentNumberLabel");
	public static final By IncidentReportIframe = By.xpath("//iframe[contains(@src,'VSOC')]");
	public static final By SearchInput = By.id("txtKeywords"); 
	public static final  String IRShowInGrid = "//td[contains(text(),'%s')]/.."; 
	public static final By IframeForEntity = By.id("//iframe[contains(@src,'ConcurStatus')]");
	//----Incident Report---General Information----	
	public static final By IncidentType = By.cssSelector("#DpIRTypeList .custom-select span");
	public static final By IncidentTypeInput = By.cssSelector("#DpIRTypeList .custom-select div input");
	public static final By TitleInput = By.name("ucIRGInfo$titleTextbox");
	public static final By AddRecipientsLink = By.id("ucIRGInfo_CcRecipientsModule_lnkRecipient");
	public static final String  AddRecipients = "//option[contains(text(),'%s')]";
	public static final By AddRecipientsUserSign = By.id("addressBook_btnAddUserSign");
	public static final By AddRecipientsGroupSign = By.id("addressBook_btnAddGroupSign");
	public static final By AddRecipientsDoneButt = By.id("addressBook_btnDone");
	
	public static final By NotifyRecipientOnce = By.id("ucIRGInfo_chkNotifyRecipientOnce");
	public static final By IncidentEndedONDate = By.cssSelector("#ucIRGInfo_IncidentEndedOn_DatePanel img");
	public static final By IncidentOccuredOn_Date = By.cssSelector("#ucIRGInfo_IncidentOccuredOn_DatePanel img");
	public static final By PickTodayOnOccured = By.cssSelector(".ui-datepicker-today");
	public static final String SelectDay = "//a[contains(text(),'%s')]";
	public static final By OneTimeEmailOnSave = By.id("ucIRGInfo_otherEmailTextbox");
	public static final By UseEmailNotiAssignRules = By.id("ucIRGInfo_chkAssignmentRules");	
	public static final By NotifyCCRecipients = By.id("ucIRGInfo_chkNotifyRecipients");
	public static final By Creator_OnIRCreateandEdit = By.id("ucIRGInfo_chkNotifyCreator");
	public static final By Creator_OnIRClose = By.id("ucIRGInfo_cb_IR_notifyCreatorClose");
	public static final By Creator_OnAssigneeReassign = By.id("ucIRGInfo_cbNotifyCreatorReassign");
	public static final By Assignee_OnIRCreateandEdit = By.id("ucIRGInfo_chkNotifyAssignee");
	public static final By Assignee_OnIRClose = By.id("ucIRGInfo_cb_IR_notifyAssigneeClose");
	public static final By Assignee_OnAssigneeReassign = By.id("ucIRGInfo_cbNotifyAssigneeReassign");
	
	
	//----Case Management---	
	public static final By OwnerSiteBox = By.xpath("//tr[12]/td[1]/div[1]/div[1]/div[1]/div[2]/span[1]/span[1]/span[1]");
	public static final By OwnerSiteDropdown = By.xpath("//div[@class='k-animation-container' ]//ul//li");
	public static final By CreateButt = By.id("create_case");
	public static final By CaseNumber = By.xpath("//td[contains(text(),'Case Number')]/..//td[@class='txt'][1]");
	public static final By CaseSearchBox = By.cssSelector(".searchbox-text");
	public static final By OkButtOnAlert = By.cssSelector(".k-button");
	public static final By Preview = By.xpath("//div[contains(text(),'Preview')]");
	
	
	
}
