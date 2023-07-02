package com.holinova.pageobject.locator;

import org.openqa.selenium.By;

/**
 * Baidu Page Objects: Element Positioning
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20
 */
public class BaiduLocator {
    /**
     * Baidu homepage search box positioning
     */
    public static final By SEARCH_INPUT = By.xpath("//input[@id='kw']");

    /**
     * Baidu homepage search button positioning
     */
    public static final By SEARCH_BUTTON = By.xpath("//input[@id='su']");
}
