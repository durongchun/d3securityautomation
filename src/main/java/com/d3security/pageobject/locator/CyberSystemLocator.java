package com.d3security.pageobject.locator;

import org.openqa.selenium.By;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CyberSystemLocator {
	//-----Site----
	public final String url = "https://poc.bemimo.com/CasePlaybookDemo/VSOC/login.aspx";
	
	//-----Login Page----
	public static final By UserName = By.id("username");
	public static final  By PassWord = By.id("password");
	public static final  By SubmitButt = By.id("Button_Login");
	
	//----Launch page---
	 public static final By LaunchVSOC = By.className("button-submit");
	 public static final By IframeID = By.id("FrameOnLifeServe");

	//----Launch page---	
	public static final By InvestigationDashboard = By.xpath("//div[@id='savedSearch']//div[contains(text(), 'Investigation Dashboard')]"); 
}
