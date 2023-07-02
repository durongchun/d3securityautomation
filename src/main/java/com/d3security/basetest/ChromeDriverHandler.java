package com.d3security.basetest;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.d3security.util.PropertiesReader;

/**
 * Chrome driver configuration
 *
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20 18:52
 */
public class ChromeDriverHandler extends DriverHandler {
    /**
     * Start local chrome
     *
     * @param browserName 
     * @param terminal    
     * @param deviceName  
     * @return WebDriver
     */
    @Override
    public WebDriver startBrowser(String browserName, String terminal, String deviceName) {
        /* if not chrome, go next one */
        if (!browserName.toLowerCase().equals("chrome")) {
            return next.startBrowser(browserName, terminal, deviceName);
        }

        /* Driver configuration environment variables */
        // Driver root path /target/test-classes/driver
        String driverParentPath = this.getClass().getResource("/").getPath() + "driver" + File.separator;
        // Chrome driver path
        String chromeDriverPath = driverParentPath + PropertiesReader.getKey("driver.chromeDriver");
        // System variables set chrome driver
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        /* Set download path  */
        String downloadPath = System.getProperty("user.dir") + File.separator + PropertiesReader.getKey("driver.downloadPath");
        Map<String, Object> downloadMap = new HashMap<>();
        downloadMap.put("download.default_directory", downloadPath);

        /* Set driver options */
        ChromeOptions chromeOptions = new ChromeOptions();
        // Set download path
        chromeOptions.setExperimentalOption("prefs", downloadMap);
        // --No-sandbox
        chromeOptions.addArguments("--no-sandbox");
        // --Disable-dev-shm-usage
        chromeOptions.addArguments("--disable-dev-shm-usage");

        /* Mobile phone browser h5 */
        if (terminal.toLowerCase().equals("h5")) {
            Map<String, String> mobileMap = new HashMap<>();
            mobileMap.put("deviceName", deviceName);
            // Set mobile phone model of h5
            chromeOptions.setExperimentalOption("mobileEmulation", mobileMap);
        }

        /* Start WebDriver */
        return new ChromeDriver(chromeOptions);
    }

    /**
     * Start remote chrome
     *
     * @param browserName    
     * @param terminal       
     * @param deviceName     
     * @param remoteIP       
     * @param remotePort     
     * @param browserVersion 
     * @return WebDriver
     */
    @Override
    public WebDriver startBrowser(String browserName, String terminal, String deviceName, String remoteIP, int remotePort, String browserVersion) {
        /* If not chrome, go next one */
        if (!browserName.toLowerCase().equals("chrome")) {
            return next.startBrowser(browserName, terminal, deviceName, remoteIP, remotePort, browserVersion);
        }

        /* Set download path */
        String downloadPath = System.getProperty("user.dir") + File.separator + PropertiesReader.getKey("driver.downloadPath");
        Map<String, Object> downloadMap = new HashMap<>();
        downloadMap.put("download.default_directory", downloadPath);

        /* Set driver options */
        // Set remote browser version
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities("chrome", browserVersion, Platform.ANY);
        ChromeOptions chromeOptions = new ChromeOptions().merge(desiredCapabilities);
        // Set download path
        chromeOptions.setExperimentalOption("prefs", downloadMap);
        // --No-sandbox
        chromeOptions.addArguments("--no-sandbox");
        // --Disable-dev-shm-usage
        chromeOptions.addArguments("--disable-dev-shm-usage");

        /* Mobile phone browser h5 */
        if (terminal.toLowerCase().equals("h5")) {
            Map<String, String> mobileMap = new HashMap<>();
            mobileMap.put("deviceName", deviceName);
            // Set mobile phone mode
            chromeOptions.setExperimentalOption("mobileEmulation", mobileMap);
        }

        /* Start RemoteWebDriver */
        URL url = null;
        try {
			url = new URL("http://" + remoteIP + ":" + remotePort + "/wd/hub/"); 			
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new RemoteWebDriver(url, chromeOptions);
    }
}
