package com.d3security.testcase;

import org.testng.annotations.Test;

import com.d3security.basetest.BaseTest;
import com.d3security.pageobject.data.dispatch.DispatchAddAndEditData;
import com.d3security.pageobject.data.dispatch.DispatchOfficerClearData;
import com.d3security.pageobject.locator.PhysicSystemLocator;
import com.d3security.pageobject.page.LoginPage;
import com.d3security.pageobject.page.SiteConfiguration;
import com.d3security.pageobject.page.physicsystem.DashboardPage;
import com.d3security.pageobject.page.physicsystem.Dispatch;
import com.d3security.pageobject.page.physicsystem.HeaderPage;
import com.d3security.util.dataprovider.ExcelDataProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DispatchTest extends BaseTest {

	@Test(dataProviderClass = ExcelDataProvider.class, dataProvider = "PhysicDispatchData", description = "Physic Dispatch Clear Officer", priority = 1)
	public void testPhysicDispatchClearOfficer(Object[] dataObject) {
		DispatchOfficerClearData dispatchOfficerClearData = new DispatchOfficerClearData();
		dispatchOfficerClearData = (DispatchOfficerClearData) dataObject[0];

		PhysicSystemLocator physicSystemLocator = new PhysicSystemLocator();
		new SiteConfiguration(driver).enterPage(physicSystemLocator.disneyurl);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.signInPhysic(dispatchOfficerClearData.getUserName(), dispatchOfficerClearData.getPassWord());
		// loginPage.LaunchVSOCManually();

		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.isDashboardExistingAndDisplayed();
		
		Dispatch dispatch = new Dispatch(driver);	
		
		if (dispatch.isOfficerAssigned(dispatchOfficerClearData.getOfficerName())) {
			dispatch.clearOfficer(dispatchOfficerClearData.getOfficerName());
		}
		
		dispatch.searchDispatch(dispatchOfficerClearData.getDispatchID());
		
		dispatch.assignOfficer(dispatchOfficerClearData.getOfficerID());
		
		dispatch.clearOfficer(dispatchOfficerClearData.getOfficerName());

		driver.manage().deleteAllCookies();
	}
	
	@Test(dataProviderClass = ExcelDataProvider.class, dataProvider = "PhysicDispatchData", description = "Physic Dispatch Clear Officer", priority = 1)
	public void testPhysicNewDispatchAndEdit(Object[] dataObject) {
		DispatchAddAndEditData dispatchAddAndEditData = new DispatchAddAndEditData();
		dispatchAddAndEditData = (DispatchAddAndEditData) dataObject[0];

		PhysicSystemLocator physicSystemLocator = new PhysicSystemLocator();
		new SiteConfiguration(driver).enterPage(physicSystemLocator.disneyurl);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.signInPhysic(dispatchAddAndEditData.getUserName(), dispatchAddAndEditData.getPassWord());
		// loginPage.LaunchVSOCManually();

		DashboardPage dashboard = new DashboardPage(driver);
		dashboard.isDashboardExistingAndDisplayed();
		
		Dispatch dispatch = new Dispatch(driver);
		
		dispatch.clickNewDispatch();
		
		dispatch.selectDispatchType(dispatchAddAndEditData.getDispatchType());
		
		dispatch.selectCheckbox();
		
		dispatch.inputSearchTextBox(dispatchAddAndEditData.getSearchableTextbox());
		dispatch.inputTextArea(dispatchAddAndEditData.getTextArea());
		
		final String dispatchID = dispatch.getDispatchNumber(dispatchAddAndEditData.getDispatchType());
		
		dispatch.searchDispatch(dispatchID);
		
		dispatch.assignOfficer(dispatchAddAndEditData.getOfficerID());
		
		dispatch.clearOfficer(dispatchAddAndEditData.getOfficerName());

		driver.manage().deleteAllCookies();
	}


	

}
