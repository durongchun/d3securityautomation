package com.holinova.pageobject.page;

import com.holinova.basepage.BasePage;
import com.holinova.pageobject.data.BaiduData;
import com.holinova.pageobject.locator.BaiduLocator;
import com.holinova.util.RedisUtil;

import bsh.This;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Baidu page object
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20
 */
@Slf4j
public class BaiduPage extends BasePage {
    /**
     * Constructor 1
     *
     * @param driver 
     */
    public BaiduPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Constructor 2
     *
     * @param driver    
     * @param redisUtil redis 
     */
    public BaiduPage(WebDriver driver, RedisUtil redisUtil) {
        super(driver, redisUtil);
    }

    /**
     * Go Baidu
     */
    public void enterPage() {
        log.info("Go to Baidu.com");
        super.enterPage(BaiduData.URL);
    }

    /**
     * Search
     *
     * @return 
     */
    public boolean search() {
        log.info("Search");        
        sendInput(BaiduLocator.SEARCH_INPUT, BaiduData.TEXT);        
        clickButton(BaiduLocator.SEARCH_BUTTON);

        /*
         * redisUtil class is a layer of encapsulation for jedisPool and jedis, all key-value valid time are set in the configuration file
         * redisUtil set expiration time for reading key-value, which is set in properties file, if can't read config file, it means unlimited time.         
         *
         * redisUtil.setKey("a", "1");
         * System.out.println("redis 中的value为：" + redisUtil.getKey("a"));
         */

        
        return ifTitleContains(BaiduData.TEXT);
    }
}
