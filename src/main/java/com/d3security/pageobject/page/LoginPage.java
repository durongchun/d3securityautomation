package com.d3security.pageobject.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.d3security.basepage.BasePage;
import com.d3security.pageobject.data.CyberSystemData;
import com.d3security.pageobject.data.PhysicSystemData;
import com.d3security.pageobject.locator.PhysicSystemLocator;
import com.d3security.util.RedisUtil;
import com.d3security.util.StepInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginPage extends BasePage {
	 /**
     * Constructor 1
     *
     * @param driver 
     */
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Constructor 2
     *
     * @param driver    
     * @param redisUtil redis 
     */
    public LoginPage(WebDriver driver, RedisUtil redisUtil) {
        super(driver, redisUtil);
    }
    
    /**
     * Go login page
     */
    public void enterPage() {
        log.info("Go login page");
        // todo : super.enterPage(LoginData.URL);
    }

    /**
     * Login by UI clicking
     */
    public void loginByUI() {
        // todo : login by UI 
    }

    /**
     * Login by API 
     * HttpClient
     */
    public void loginByAPI() {
        // todo : login by API 
    }

    /**
     * Login by cookie/sessionid/access_token 
     * Use WebDriver to setCookies or save value in browser by js
     */
    public void loginBySession() {
        // todo : login by cookie/sessionid/access_token 
    }

	public void signInPhysic(String userName, String passWord) {
		StepInfo.addMessage("Sign In");
		sendInput(PhysicSystemLocator.UserName, userName);
		sendInput(PhysicSystemLocator.PassWord, passWord);
		clickElement(PhysicSystemLocator.SubmitButt);
		waitForJQueryToLoad();
		waitForPageToRefresh();
	}

	public void signInCyber(CyberSystemData cyberSystemData) {
		sendInput(PhysicSystemLocator.UserName, cyberSystemData.getUserName());
		sendInput(PhysicSystemLocator.PassWord, cyberSystemData.getPassWord());
		clickElement(PhysicSystemLocator.SubmitButt);
	}

	public void LaunchVSOCManually() {
		if (isElementExists(driver, PhysicSystemLocator.LaunchVSOC)) {
			log.info("Launch VSOC Manually");
			clickElement(PhysicSystemLocator.LaunchVSOC);		
			driver.navigate().refresh();		
			waitForSeconds(8);
			waitForJQueryToLoad();
			waitForPageToRefresh();
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		

	}
	
	public boolean isLaunchVSOCDisplaying() {
		if (isElementExists(driver, PhysicSystemLocator.LaunchVSOC)) {
			return true;
		}else {
			return false;
		}
		
	}
}
