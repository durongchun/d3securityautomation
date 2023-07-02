package com.holinova.testcase;

import com.holinova.basetest.BaseTest;
import com.holinova.pageobject.page.AbcnullPage;

import org.testng.annotations.Test;

/**
 * Lucy
 *
 * @author lucy
 * @version 1.0.0
 * @date 2020/9/28
 */
public class AbcnullTest extends BaseTest {
    private AbcnullPage abcnullPage;

    @Test(description = "abcnull search page", priority = 1)
    public void testSearch() {
        
        abcnullPage = new AbcnullPage(driver, redisUtil);
        
        abcnullPage.enterPage();
        
        assert abcnullPage.search();
    }
}
