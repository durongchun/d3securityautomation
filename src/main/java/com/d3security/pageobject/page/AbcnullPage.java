package com.holinova.pageobject.page;

import com.holinova.basepage.BasePage;
import com.holinova.pageobject.data.AbcnullData;
import com.holinova.pageobject.locator.AbcnullLocator;
import com.holinova.util.RedisUtil;

import bsh.This;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abcnull page object
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20
 */
@Slf4j
public class AbcnullPage extends BasePage {
    /**
     * Constructor 1
     *
     * @param driver
     */
    public AbcnullPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Constructor 2
     *
     * @param driver    
     * @param redisUtil 
     */
    public AbcnullPage(WebDriver driver, RedisUtil redisUtil) {
        super(driver, redisUtil);
    }

    /**
     * Go abcnull page
     */
    public void enterPage() {
        log.info("Go abcnull page");
        super.enterPage(AbcnullData.URL);
    }

    /**
     * Search
     *
     * @return 
     */
    public boolean search() {
        log.info("Search");       
        sendInput(AbcnullLocator.SEARCH_INPUT, AbcnullData.BLOGTITLE);        
        clickButton(AbcnullLocator.SEARCH_BUTTON);
        /*
         * A new page will be generated after the search
         * there will be one more data page, so I have three pages here
         * For those who do not have a data page, it is recommended to use the switchNextHandle method
         */
        switchHandleByNum(3);
        // Return whether to enter the specified page
        return ifTitleContains(AbcnullData.BLOGTITLE);
    }
}
