package com.holinova.testcase;

import com.holinova.basetest.BaseTest;
import com.holinova.pageobject.page.BaiduPage;

import org.testng.annotations.Test;

/**
 * Baidu search page
 *
 * @author lucy
 * @version 1.0.0
 * @date 2020/9/28
 */
public class BaiduTest extends BaseTest {
    private BaiduPage baiduPage;

    @Test(description = "baidu search page", priority = 1)
    public void testSearch() {
        
        baiduPage = new BaiduPage(driver, redisUtil);
       
        baiduPage.enterPage();
        
        assert baiduPage.search();
    }
}
