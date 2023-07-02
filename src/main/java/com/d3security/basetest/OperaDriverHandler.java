package com.d3security.basetest;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.d3security.util.PropertiesReader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Opera driver config
 * @author Lucy
 * @version 1.0.0
 * @date 2022/10/20 18:53
 */
public class OperaDriverHandler extends DriverHandler {
    /**
     * Starts local opera     
     *
     * @param browserName 
     * @param terminal     pc/h5
     * @param deviceName  
     * @return WebDriver
     */
    @Override
    public WebDriver startBrowser(String browserName, String terminal, String deviceName) {
        /* If not opera, go next */
        if (!browserName.toLowerCase().equals("opera")) {
            return next.startBrowser(browserName, terminal, deviceName);
        }

        /* Driver config and environment variable */
        // Driver root path /target/test-classes/driver
        String driverParentPath = this.getClass().getResource("/").getPath() + "driver" + File.separator;
        // Opera driver path
        String operaDriverPath = driverParentPath + PropertiesReader.getKey("driver.operaDriver");
        // Set system variable
        System.setProperty("webdriver.opera.driver", operaDriverPath);

        /* Set download path */
        String downloadPath = System.getProperty("user.dir") + File.separator + PropertiesReader.getKey("driver.downloadPath");
        Map<String, Object> downloadMap = new HashMap<>();
        downloadMap.put("download.default_directory", downloadPath);

        /* Set driver options */
        OperaOptions operaOptions = new OperaOptions();
        // Config download path
        operaOptions.setExperimentalOption("prefs", downloadMap);
        // --no-sandbox
        operaOptions.addArguments("--no-sandbox");
        // --disable-dev-shm-usage
        operaOptions.addArguments("--disable-dev-shm-usage");

        /* todo : mobile phone browser test h5 */

        /* Start WebDriver */
        return new OperaDriver(operaOptions);
    }

    /**
     * Starts remote opera    
     *
     * @param browserName    
     * @param terminal       pc/h5
     * @param deviceName     
     * @param remoteIP        ip
     * @param remotePort     
     * @param browserVersion 
     * @return WebDriver
     */
    @Override
    public WebDriver startBrowser(String browserName, String terminal, String deviceName, String remoteIP, int remotePort, String browserVersion) {
        /* If not opera, go next */
        if (!browserName.toLowerCase().equals("opera")) {
            return next.startBrowser(browserName, terminal, deviceName, remoteIP, remotePort, browserVersion);
        }

        /* Set download path */
        String downloadPath = System.getProperty("user.dir") + File.separator + PropertiesReader.getKey("driver.downloadPath");
        Map<String, Object> downloadMap = new HashMap<>();
        downloadMap.put("download.default_directory", downloadPath);

        /* Config driver options */
        // Set remote browser version
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities("opera", browserVersion, Platform.ANY);
        OperaOptions operaOptions = new OperaOptions().merge(desiredCapabilities);
        // Set download path
        operaOptions.setExperimentalOption("prefs", downloadMap);
        // --no-sandbox
        operaOptions.addArguments("--no-sandbox");
        // --disable-dev-shm-usage
        operaOptions.addArguments("--disable-dev-shm-usage");

        /* todo : mobile phone browser test h5 */

        /* Start RemoteWebDriver */
        URL url = null;
        try {
            url = new URL("http://" + remoteIP + ":" + remotePort + "/wd/hub/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return new RemoteWebDriver(url, operaOptions);
    }
}
