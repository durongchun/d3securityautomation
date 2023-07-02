package com.d3security.basetest;

import org.openqa.selenium.WebDriver;

/**
 * Driver chain node
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/19 18:52
 */
public class HeadHandler extends DriverHandler {
    /**
     * Start local browser
     *
     * @param browserName 
     * @param terminal     pc/h5
     * @param deviceName  
     * @return WebDriver
     */
    @Override
    public WebDriver startBrowser(String browserName, String terminal, String deviceName) {
        return next.startBrowser(browserName, terminal, deviceName);
    }

    /**
     * Start remote browser
     *
     * @param browserName    
     * @param terminal        pc/h5
     * @param deviceName     
     * @param remoteIP        ip
     * @param remotePort     
     * @param browserVersion 
     * @return WebDriver
     */
    @Override
    public WebDriver startBrowser(String browserName, String terminal, String deviceName, String remoteIP, int remotePort, String browserVersion) {
        return next.startBrowser(browserName, terminal, deviceName, remoteIP, remotePort, browserVersion);
    }
}
