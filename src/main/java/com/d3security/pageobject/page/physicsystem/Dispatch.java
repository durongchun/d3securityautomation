package com.d3security.pageobject.page.physicsystem;

import org.openqa.selenium.WebDriver;

import java.util.Set;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.d3security.basepage.BasePage;
import com.d3security.pageobject.locator.PhysicSystemLocator;
import com.d3security.util.StepInfo;


public class Dispatch extends BasePage{
	
	public Dispatch(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void clickNewDispatch() {
		clickElement(PhysicSystemLocator.NewDispatchButt);
	}
	
	public void selectDispatchType(String type) {
		clickElement(PhysicSystemLocator.DispatchType);
		sendInput(PhysicSystemLocator.DispatchTypeInput, type);
	}
	
	public void selectDispatchSection(String section) {
		clickElement(By.xpath(String.format(PhysicSystemLocator.DispatchSection, section)));
	}
	
	public void selectCheckbox() {
		clickElement(PhysicSystemLocator.DispatchCheckBox1);
		clickElement(PhysicSystemLocator.DispatchCheckBox2);
		clickElement(PhysicSystemLocator.DispatchCheckBox3);
		clickElement(PhysicSystemLocator.DispatchCheckBox4);	
	}
	
	public void inputSearchTextBox(String text) {
		sendInput(PhysicSystemLocator.DispatchSearchableTextBox, text);
		
	}
	
	public void inputTextArea(String text) {
		sendInput(PhysicSystemLocator.DispatchTextArea, text);
	}
	
	
	public void assignOfficer(String officerID) {		
		clickElement(By.xpath(String.format(PhysicSystemLocator.OfficerID, officerID)));
		clickElement(PhysicSystemLocator.AssignButt);
	}
	
	public void clearOfficer(String officerName) {
		clickElement(By.xpath(String.format(PhysicSystemLocator.OfficerInQ4, officerName)));
		clickElement(PhysicSystemLocator.DispatchClear);	
		clickElement(PhysicSystemLocator.OkButtOnAlert);
	}
	
	
	public void searchDispatch(String number) {
		sendInput(PhysicSystemLocator.DispatchSearchBox, number);
		clickElement(By.xpath(String.format(PhysicSystemLocator.DispatchID, number)));
	}
	
	public String getDispatchNumber(String type) {
		String textString = driver.findElement(By.xpath(String.format(PhysicSystemLocator.DispatchNumber, type))).getText();
		return textString.split("-")[1];
	}
	
	public boolean isOfficerAssigned(String name) {		
		return isElementExists(driver, By.xpath(String.format(PhysicSystemLocator.OfficerInQ4, name)));
		
	}
	
	

}
