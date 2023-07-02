package com.d3security.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.d3security.util.PropertiesReader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * IE driver config
 * *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20 18:54
 */
public class InternetExplorerDriverHandler extends DriverHandler {
    /**
     * Start local IE
     * @param browserName 
     * @param terminal    pc/h5
     * @param deviceName  
     * @return WebDriver
     */
    @Override
    public WebDriver startBrowser(String browserName, String terminal, String deviceName) {
        /* If not IE, go to next */
        if (!browserName.toLowerCase().equals("ie")) {
            return next.startBrowser(browserName, terminal, deviceName);
        }

        /* Driver config and environment variable */
        // Driver root path /target/test-classes/driver
        String driverParentPath = this.getClass().getResource("/").getPath() + "driver" + File.separator;
        // IE driver path
        String ieDriverPath = driverParentPath + PropertiesReader.getKey("driver.ieDriver");
        // Set system variable IE driver
        System.setProperty("webdriver.ie.driver", ieDriverPath);

        /* Set download path */
        String downloadPath = System.getProperty("user.dir") + File.separator + PropertiesReader.getKey("driver.downloadPath");

        /* Set driver options */
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
        // Ignore security
        desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions().merge(desiredCapabilities);

        /* todo : mobile phone browser test h5 */

        /* Starts WebDriver */
        return new InternetExplorerDriver(internetExplorerOptions);
    }

    /**
     * Starts remote IE    
     *
     * @param browserName    
     * @param terminal       pc/h5
     * @param deviceName     
     * @param remoteIP       ip
     * @param remotePort     
     * @param browserVersion 
     * @return WebDriver
     */
    @Override
    public WebDriver startBrowser(String browserName, String terminal, String deviceName, String remoteIP, int remotePort, String browserVersion) {
        /* If not IE, go next */
        if (!browserName.toLowerCase().equals("ie")) {
            return next.startBrowser(browserName, terminal, deviceName, remoteIP, remotePort, browserVersion);
        }

        /* todo : set download path */
        String downloadPath = System.getProperty("user.dir") + File.separator + PropertiesReader.getKey("driver.downloadPath");

        /* Set driver options */
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
        // Ignore security
        desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions().merge(desiredCapabilities);

        /* todo : mobile phone browser test h5 */

        /* Start RemoteWebDriver */
        URL url = null;
        try {
            url = new URL("http://" + remoteIP + ":" + remotePort + "/wd/hub/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new RemoteWebDriver(url, internetExplorerOptions);
    }
}
