package com.d3security.basetest;

import org.openqa.selenium.WebDriver;

import com.d3security.exception.BrowserNameException;

/**
 * Driver node
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20 18:52
 */
public class TailHandler extends DriverHandler {
    /**
     * @param browserName 
     * @param terminal    pc/h5
     * @param deviceName  
     * @return WebDriver
     * @throws Exception  can't match browserName 
     */
    @Override
    public WebDriver startBrowser(String browserName, String terminal, String deviceName) {
        throw new BrowserNameException("Don't support browser" + "(" + browserName + ")");
    }

    /**
     * @param browserName    
     * @param terminal       pc/h5
     * @param deviceName     
     * @param remoteIP       ip
     * @param remotePort     
     * @param browserVersion 
     * @return WebDriver
     * @throws Exception  can't match browserName 
     */
    @Override
    public WebDriver startBrowser(String browserName, String terminal, String deviceName, String remoteIP, int remotePort, String browserVersion) {
        throw new BrowserNameException("Don't support browser" + "(" + browserName + ")");
    }
}
