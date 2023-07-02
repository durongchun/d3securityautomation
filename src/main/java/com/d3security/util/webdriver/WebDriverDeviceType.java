package com.d3security.util.webdriver;

/**
 * Enum for different deviceTypes - browser, phone and tablet.
 *
 * @author Lucy Du
 */
public enum WebDriverDeviceType {
    DESKTOP, PHONE, TABLET;

    public static WebDriverDeviceType fromString(String deviceTypeParam) {
        if (deviceTypeParam != null) {
            for (WebDriverDeviceType deviceType : WebDriverDeviceType.values()) {
                if (deviceTypeParam.equalsIgnoreCase(deviceType.toString())) {
                    return deviceType;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}