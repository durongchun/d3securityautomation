package com.holinova.testcase;

import org.testng.annotations.Test;

import com.holinova.basetest.BaseTest;
import com.holinova.pageobject.data.ErpSystemData;
import com.holinova.pageobject.page.erpsystem.BookDemoPage;
import com.holinova.pageobject.page.erpsystem.SiteConfiguration;
import com.holinova.util.dataprovider.DatabaseParser;
import com.holinova.util.dataprovider.ExcelDataProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErpSystemTest extends BaseTest {

	@Test(dataProviderClass = ExcelDataProvider.class, dataProvider = "ERPData", description = "ERP Register", priority = 1)
	public void testERPBusinessFlow(Object[] dataObject) {
		ErpSystemData erpSystemData = new ErpSystemData();
		erpSystemData = (ErpSystemData) dataObject[0];		
		
		new SiteConfiguration(driver).enterPage();
		new BookDemoPage(driver).bookDemo(erpSystemData);
				
		DatabaseParser databaseParser = new DatabaseParser(driver);
		databaseParser.validateDatabaseResponseData();
		
		driver.manage().deleteAllCookies();
	}

}
