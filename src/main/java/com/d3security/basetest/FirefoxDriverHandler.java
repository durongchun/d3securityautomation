package com.d3security.basetest;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.d3security.util.PropertiesReader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Firefox driver configuration 
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/19 18:53
 */
public class FirefoxDriverHandler extends DriverHandler {
    /**
     * Start local firefox   
     * @param browserName 
     * @param terminal     pc/h5
     * @param deviceName  
     * @return WebDriver
     */
    @Override
    public WebDriver startBrowser(String browserName, String terminal, String deviceName) {
        /* If not firefox, go next one */
        if (!browserName.toLowerCase().equals("firefox")) {
            return next.startBrowser(browserName, terminal, deviceName);
        }

        /* Driver configuration into environment variables */
        // Driver path /target/test-classes/driver
        String driverParentPath = this.getClass().getResource("/").getPath() + "driver" + File.separator;
        // Driver firefox path
        String firefoxDriverPath = driverParentPath + PropertiesReader.getKey("driver.firefoxDriver");
        // System variables sets firefox driver
        System.setProperty("webdriver.gecko.driver", firefoxDriverPath);

        /* Set download path */
        String downloadPath = System.getProperty("user.dir") + File.separator + PropertiesReader.getKey("driver.downloadPath");
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        // 0 : desktop, 1 : "my download", 2 : self-definition
        firefoxProfile.setPreference("browser.download.folderList", "2");
        firefoxProfile.setPreference("browser.download.dir", downloadPath);

        /* Driver options configuration */
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        // Set download path
        firefoxOptions.setProfile(firefoxProfile);
        // --No-sandbox
        firefoxOptions.addArguments("--no-sandbox");
        // --Disable-dev-shm-usage
        firefoxOptions.addArguments("--disable-dev-shm-usage");

        /* Mobile phone browser h5 */

        /* Start WebDriver */
        return new FirefoxDriver(firefoxOptions);
    }

    /**
     * Start remote firefox     
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
        /* If not firefox, go next one */
        if (!browserName.toLowerCase().equals("firefox")) {
            return next.startBrowser(browserName, terminal, deviceName, remoteIP, remotePort, browserVersion);
        }

        /* Set download path */
        String downloadPath = System.getProperty("user.dir") + File.separator + PropertiesReader.getKey("driver.downloadPath");
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        // 0:desktop，1:my download，2:self definition
        firefoxProfile.setPreference("browser.download.folderList", "2");
        firefoxProfile.setPreference("browser.download.dir", downloadPath);

        /* Driver options */
        // Set remote browser version
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities("firefox", browserVersion, Platform.ANY);
        FirefoxOptions firefoxOptions = new FirefoxOptions().merge(desiredCapabilities);
        // Set download path
        firefoxOptions.setProfile(firefoxProfile);
        // --no-sandbox
        firefoxOptions.addArguments("--no-sandbox");
        // --disable-dev-shm-usage
        firefoxOptions.addArguments("--disable-dev-shm-usage");

        /* todo : mobile phone browser h5 */

        /* Start RemoteWebDriver */
        URL url = null;
        try {
            url = new URL("http://" + remoteIP + ":" + remotePort + "/wd/hub/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new RemoteWebDriver(url, firefoxOptions);
    }
}
