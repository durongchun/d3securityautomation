package com.d3security.util.webdriver;

/**
 * Enum for all different kind of browsers
 *
 * @author Lucy Du
 */
public enum WebDriverBrowser {
    HTML_UNIT("htmlunit"),
    FIREFOX("firefox"),
    CHROME("chrome"),
    SAFARI("safari"),
    INTERNET_EXPLORER("internet explorer"),
    IE("ie"),
    NATIVE_ANDROID_BROWSER("androidBrowser"),
    AMAZON_SILK("silk"),
    APP("app"); // APP is for testing Web apps on mobile.

    private String browser;

    private WebDriverBrowser(final String browser) {
        this.browser = browser;
    }

    public static WebDriverBrowser fromString(String browserParam) {
        if (browserParam != null) {
            for (WebDriverBrowser browser : WebDriverBrowser.values()) {
                if (browserParam.toString().replaceAll("[0-9]*", "").trim()
                        .toLowerCase().equalsIgnoreCase(browser.getValue())) {
                    return browser;
                }
            }
        }
        return null;
    }

    public String getValue() {
        return browser;
    }

    @Override
    public String toString() {
        return browser;
    }
}