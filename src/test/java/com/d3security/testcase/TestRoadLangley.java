package com.holinova.testcase;

import org.testng.annotations.Test;

import com.holinova.basepage.BaseBrowser;
import com.holinova.basetest.BaseTest;
import com.holinova.pageobject.data.RoadTestData;
import com.holinova.pageobject.locator.RoadTestLocator;
import com.holinova.pageobject.page.roadtest.RoatTestPage;
import com.holinova.util.dataprovider.ExcelDataProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestRoadLangley extends BaseTest {

	@Test(dataProviderClass = ExcelDataProvider.class, dataProvider = "RoadData", description = "Road Test", priority = 1)
	public void testRoadRegister(Object[] dataObject) throws Exception {
		BaseBrowser baseBrowser = new BaseBrowser(driver);
		RoadTestData roadTestData = new RoadTestData();
		roadTestData = (RoadTestData) dataObject[0];

		RoatTestPage roadTest = new RoatTestPage(driver);
		roadTest.enterPage(roadTestData);
		roadTest.signIn(roadTestData);
		if (roadTest.isReschedule()) {
			roadTest.clickRescheduleAppointment();
		}

		roadTest.inputLocation(roadTestData);
		int timeTemp = 10000;
		while (true) {
			Thread.sleep(timeTemp);
			log.info("wait time="+timeTemp/1000+" SECONDS");
			roadTest.findLanyleyWillow();
			baseBrowser.isPageLoaded();
			if (roadTest.findOctDate() == true) {
				roadTest.clickButton(RoadTestLocator.REVIEW_APPOINTMENT);
				Thread.sleep(3000);
				roadTest.clickButton(RoadTestLocator.NEXT);
				Thread.sleep(2000);
				roadTest.clickSmsOption();
				Thread.sleep(2000);
				roadTest.clickButton(RoadTestLocator.SENT);		
				Thread.sleep(3000);
//				timeTemp=10000;
				break;
			}

			if (roadTest.isErroTextDisplayed()) {
				log.info("wait time= 0.5 Hours");
				Thread.sleep(1*30*60*1000);
//				break;
			}			
			driver.findElement(RoadTestLocator.LANGLEY_WILLOW);
		}
	}

}
