package com.d3security.pageobject.locator;

import org.openqa.selenium.By;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PhysicSystemLocator {
	//-----Site----
	public final String url = "https://tch.d3securityonline.com/VSOC.TCH";
	
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
	public static final By ArrestDetails = By.xpath("//span[contains(text(),'Arrest Details')]");
	public static final By NoCheckboxForArrestDetails = By.id("DFC2_dcElement7031_1");
	public static final By WorkplaceViolence = By.xpath("//span[contains(text(),'Workplace Violence')]");
	public static final By WorkplaceViolenceOption = By.id("DFC2_dcElement6828");
	public static final By SaveButt = By.id("btnSave");
	public static final By IncidentNumber = By.id("irView_IncidentNumberLabel");
	public static final By IncidentReportIframe = By.xpath("//iframe[contains(@src,'VSOC')]");
	public static final By SearchInput = By.id("txtKeywords");
	public static final  String IRShowInGrid = "//td[contains(text(),'%s')]/.."; 
	
}
