package com.d3security.util.webdriver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Enum for different device - AndroidPhone  , iphone , ipad , android Tablet
 *
 * @author Lucy Du
 */
public enum WebDriverDevice {
    ANDROID_PHONE("PHONE", "ANDROID"), IPHONE("PHONE", "IOS"), IPAD("TABLET", "IOS"), ANDROID_TABLET("TABLET", "ANDROID");
    private static final Logger logger = LogManager
            .getLogger(WebDriverDevice.class);
    private String deviceType;
    private String deviceOS;

    private WebDriverDevice(final String deviceType, final String deviceOS) {
        this.setDevice(deviceType);
        this.setDeviceOS(deviceOS);
    }

    /**
     * Get Device based on input of Device OS and DeviceType (Phone , Tablet)
     *
     * @param deviceType
     * @param deviceOS
     * @return
     */
    public static WebDriverDevice getDevice(String deviceType, String deviceOS) {
        if (deviceType != null && deviceOS != null) {
            for (WebDriverDevice driverDevice : values()) {
                if (driverDevice.getDevice().trim().equalsIgnoreCase(deviceType.trim()) && driverDevice.getDeviceOS().trim().equalsIgnoreCase(deviceOS.trim())) {
                    return driverDevice;
                }
            }
        }
        logger.fatal(String.format("device %s with device OS - %s could not be recognized . Please specify the device configuration in testng xml ", deviceType, deviceOS));
        return null;
    }

    public String getDevice() {
        return deviceType;
    }

    public void setDevice(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceOS() {
        return deviceOS;
    }

    public void setDeviceOS(String deviceOS) {
        this.deviceOS = deviceOS;
    }

    @Override
    public String toString() {
        return deviceType + " " + deviceOS;
    }

}