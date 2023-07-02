package com.d3security.basetest;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.d3security.util.PropertiesReader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Edge driver configuration
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/19 18:53
 */
public class EdgeDriverHandler extends DriverHandler {
    /**
     * Start local edge
     * @param browserName 
     * @param terminal     pc/h5
     * @param deviceName  
     * @return WebDriver
     */
    @Override
    public WebDriver startBrowser(String browserName, String terminal, String deviceName) {
        /* If not edge, go next one */
        if (!browserName.toLowerCase().equals("edge")) {
            return next.startBrowser(browserName, terminal, deviceName);
        }

        /* Set driver environment variables */
        // Driver root path  /target/test-classes/driver
        String driverParentPath = this.getClass().getResource("/").getPath() + "driver" + File.separator;
        // Edge driver path
        String edgeDriverPath = driverParentPath + PropertiesReader.getKey("driver.edgeDriver");
        // Set system variables: edge driver
        System.setProperty("webdriver.edge.driver", edgeDriverPath);

        /* Set download path */
        String downloadPath = System.getProperty("user.dir") + File.separator + PropertiesReader.getKey("driver.downloadPath");

        /* Set driver options */
        EdgeOptions edgeOptions = new EdgeOptions();

        /* Mobile phone browser h5 */

        /* Start WebDriver */
        return new EdgeDriver(edgeOptions);
    }

    /**
     * Start remote edge     
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
        /* If not edge, go next one */
        if (!browserName.toLowerCase().equals("edge")) {
            return next.startBrowser(browserName, terminal, deviceName, remoteIP, remotePort, browserVersion);
        }

        /* Set download path */
        String downloadPath = System.getProperty("user.dir") + File.separator + PropertiesReader.getKey("driver.downloadPath");

        /* Set driver options */
        // Set remote browser version
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities("edge", browserVersion, Platform.ANY);
        EdgeOptions edgeOptions = new EdgeOptions().merge(desiredCapabilities);

        /* Mobile phone browser h5 */

        /* Start RemoteWebDriver */
        URL url = null;
        try {
            url = new URL("http://" + remoteIP + ":" + remotePort + "/wd/hub/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new RemoteWebDriver(url, edgeOptions);
    }
}
