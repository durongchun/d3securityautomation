package com.d3security.basetest;

import static java.lang.Thread.sleep;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.d3security.constant.TestConstant;
import com.d3security.util.PropertiesReader;

import bsh.This;
import lombok.extern.slf4j.Slf4j;

/**
 * Driver base class
 *
 * @author Lucy Du
 * @version 1.0.0
 * @date 2022/10/20
 */
@Slf4j
public class BaseDriver {
    /**
     * Browser driver
     */
    private WebDriver driver;

    /**
     * Browser name
     */
    private String browserName;

    /**
     * Terminal choose pc or h5
     */
    private String terminal;

    /**
     * Device choose
     */
    private String deviceName;

    /**
     * Hub ip address
     */
    private String remoteIP;

    /**
     * Hub port
     */
    private int remotePort;

    /**
     * Browser version
     */
    private String browserVersion;

    /**
     * Open the corresponding browser
     *
     * @param browserName    
     * @param terminal        
     * @param deviceName     
     * @param remoteIP       
     * @param remotePort     
     * @param browserVersion 
     * @return WebDriver
     */
    public WebDriver startBrowser(String browserName, String terminal, String deviceName, String remoteIP, int remotePort, String browserVersion) {
        /* Driver basic info*/
        this.browserName = browserName.toLowerCase();
        /* Terminal device info */
        this.terminal = terminal.toLowerCase();
        this.deviceName = deviceName;
        /* Hub info configuration */
        this.remoteIP = remoteIP;
        this.remotePort = remotePort;
        this.browserVersion = browserVersion;

        /* Initialize each object in the driven chain */
        DriverHandler headHandler = new HeadHandler();
        DriverHandler chromeDriverHandler = new ChromeDriverHandler();
        DriverHandler firefoxDriverHandler = new FirefoxDriverHandler();
        DriverHandler operaDriverHandler = new OperaDriverHandler();
        DriverHandler edgeDriverHandler = new EdgeDriverHandler();
        DriverHandler internetExplorerDriverHandler = new InternetExplorerDriverHandler();
        DriverHandler tailHandler = new TailHandler();

        /* Build a complete chain for driver initialization */
        headHandler.setNext(chromeDriverHandler).setNext(firefoxDriverHandler).setNext(operaDriverHandler)
                .setNext(edgeDriverHandler).setNext(internetExplorerDriverHandler).setNext(tailHandler);

        /* Start the browser via Chain*/
        this.driver = headHandler.start(browserName, terminal, deviceName, remoteIP, remotePort, browserVersion);

        /* The driver sets the waiting time */
        long implicitlyWait = Long.parseLong(PropertiesReader.getKey("driver.timeouts.implicitlyWait"));
        long pageLoadTimeout = Long.parseLong(PropertiesReader.getKey("driver.timeouts.pageLoadTimeout"));
        long setScriptTimeout = Long.parseLong(PropertiesReader.getKey("driver.timeouts.setScriptTimeout"));
        // Implicit wait 
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        // Page load 
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
        // JS wait
        driver.manage().timeouts().setScriptTimeout(setScriptTimeout, TimeUnit.SECONDS);
        /* Window maximize */
        driver.manage().window().maximize();
        log.info((terminal.toLowerCase().equals("h5")) ? ("Browser：" + browserName + " h5 starts successfully!") : ("Browser：" + browserName + " starts successfully!"));
        
        return this.driver;
    }

    /**
     * Get browser driver
     *
     * @return browser driver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Set browser driver
     *
     * @param driver 
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Close browser
     *
     * @throws InterruptedException sleep
     */
    public void closeBrowser() throws InterruptedException {
        // JS display a pop-up to indicate the end of the test
        ((JavascriptExecutor) driver).executeScript("alert('Testing completely, browser will be closed after 3s!')");
        sleep(TestConstant.THREE_THOUSANG);
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        log.info(browserName + "Close Browser");
    }

    /**
     * GetBrowserName
     *
     * @return browserName
     */
    public String getBrowserName() {
        return browserName;
    }

    /**
     * GetTerminal
     *
     * @return terminal
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * GetDeviceName
     *
     * @return deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * GetRemoteIP
     *
     * @return remoteIP
     */
    public String getRemoteIP() {
        return remoteIP;
    }

    /**
     * GetRemotePort
     *
     * @return remotePort
     */
    public int getRemotePort() {
        return remotePort;
    }

    /**
     * GetBrowserVersion
     *
     * @return browserVersion
     */
    public String getBrowserVersion() {
        return browserVersion;
    }
}
