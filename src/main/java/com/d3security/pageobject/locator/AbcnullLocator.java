package com.holinova.pageobject.locator;

import org.openqa.selenium.By;

/**
 * abcnull 
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20
 */
public class AbcnullLocator {
    /**
     * abcnull page search box positioning
     */
    public static final By SEARCH_INPUT = By.xpath("//input[@id='toolber-keyword']");

    /**
     * abcnull  page search button positioning
     */
    public static final By SEARCH_BUTTON = By.xpath("//div[@class='search_bar onlySearch searchUser']/a/img");
}
