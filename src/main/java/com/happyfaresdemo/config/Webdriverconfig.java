package com.happyfaresdemo.config;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Webdriverconfig {
	
	public RemoteWebDriver driver;
	int count=0;
	
	public RemoteWebDriver Chromeconfig(String url) throws IOException {
		DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "chrome");
        caps.setCapability("browserVersion", "103");
        caps.setCapability("platformName", "Windows");
        ChromeOptions options = new ChromeOptions();
	    Map<String, Object> prefs = new HashMap<String, Object>();
	    Map<String, Object> profile = new HashMap<String, Object>();
	    Map<String, Object> contentSettings = new HashMap<String, Object>();
	    contentSettings.put("notifications", 1);
	    profile.put("managed_default_content_settings", contentSettings);
	    prefs.put("profile", profile);
	    options.setExperimentalOption("prefs", prefs);
	    caps.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
        driver.get(url);
        driver.manage().window().maximize();
        return driver;
	}
}
