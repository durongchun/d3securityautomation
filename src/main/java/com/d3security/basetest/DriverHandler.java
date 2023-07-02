package com.d3security.basetest;

import org.openqa.selenium.WebDriver;

/**
 * Driving the Chain of driver handler 
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/19 18:38
 */
public abstract class DriverHandler {


    /**
     * Next DriverHandler 
     */
    public DriverHandler next;

    /**
     * Identify if start local or remote
     *
     * @param browserName    
     * @param terminal       
     * @param deviceName     
     * @param remoteIP       
     * @param remotePort     
     * @param browserVersion 
     * @return WebDriver
     */
    public WebDriver start(String browserName, String terminal, String deviceName, String remoteIP, int remotePort, String browserVersion) {
        // If remoteIP is empty, it's local
        if (remoteIP == null || remoteIP.isEmpty()) {
            // If terminal is pc, deviceName can be null
            return startBrowser(browserName, terminal, deviceName);
        } else {
            return startBrowser(browserName, terminal, deviceName, remoteIP, remotePort, browserVersion);
        }
    }

    /**
     * Run local
     *
     * @param browserName 
     * @param terminal     pc/h5
     * @param deviceName  
     * @return WebDriver
     */
    public abstract WebDriver startBrowser(String browserName, String terminal, String deviceName);

    /**
     * Run remote
     *
     * @param browserName    
     * @param terminal       pc/h5
     * @param deviceName     
     * @param remoteIP       ip
     * @param remotePort     
     * @param browserVersion 
     * @return WebDriver
     */
    public abstract WebDriver startBrowser(String browserName, String terminal, String deviceName, String remoteIP, int remotePort, String browserVersion);

    /**
     * Successor node
     *
     * @param next 
     */
    public DriverHandler setNext(DriverHandler next) {
        this.next = next;
        return this.next;
    }
}
