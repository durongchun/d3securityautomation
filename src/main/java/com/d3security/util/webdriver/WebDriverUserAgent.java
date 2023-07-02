package com.d3security.util.webdriver;

/**
 * Enum for different user agents
 *
 * @author Lucy Du
 */
public enum WebDriverUserAgent {
    IPHONE("iphone", "5_1_1", "chrome"),
    ANDROID("android", "4.3", "safari"),
    DEFAULT("default", "default", "default");
    
    private static WebDriverUserAgent CURRENT;
    private String useragent;
    private String OSversion;
    private String agentbrowser;

    private WebDriverUserAgent(final String useragent, final String OSversion, final String agentbrowser) {

        this.useragent = useragent;
        this.OSversion = OSversion;
        this.agentbrowser = agentbrowser;
    }
    
 public synchronized static void setCurrent(WebDriverUserAgent useragent) {
    	
        CURRENT = useragent;
    }

    public static WebDriverUserAgent getCurrentUserAgent() {

        return CURRENT;
    }

    public static WebDriverUserAgent parse(final String useragent) {
        if (useragent == null) {
            return null;
        }
        for (final WebDriverUserAgent currentUserAgent : values()) {
            if (currentUserAgent.toString().equalsIgnoreCase(
                    useragent.toString())) {
                return currentUserAgent;
            }
        }
        return null;
    }
    
    public static WebDriverUserAgent fromString(String agentParam) {
        if (agentParam != null) {
            for (WebDriverUserAgent useragent : WebDriverUserAgent.values()) {
                if (agentParam.toString().replaceAll("[0-9]*", "").trim()
                        .toLowerCase().equalsIgnoreCase(useragent.getValue())) {
                             return useragent;
                }
            }
        }
        return null;
    }
    
    
    public String getUserAgentPreference(WebDriverUserAgent useragent)
    {
    	String preference= "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0";
    	switch (useragent) 
    	
    	{
    	case IPHONE: 
    	{
    		preference = "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25";
    		break;
    	}
    	
    	case ANDROID:
    	{
    		preference = "Mozilla/5.0 (Linux; Android 4.3; SPH-L710 Build/JSS15J) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.99 Mobile Safari/537.36";
    		break;
    	}
    	
    	default:
    	{
    		preference = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:35.0) Gecko/20100101 Firefox/35.0";
    	}
    	}
    	
    	return preference;
    }
    
    public String getOSversion() {
        return OSversion;
    }

    public void setOSversion(String OSversion) {
        this.OSversion = OSversion;
    }
    
    public String getAgentBrowser() {
        return agentbrowser;
    }

    public void setAgentBrowser(String agentbrowser) {
        this.agentbrowser = agentbrowser;
    }

    public String getValue() {
        return useragent;
    }

    @Override
    public String toString() {
        return useragent;
    }
}