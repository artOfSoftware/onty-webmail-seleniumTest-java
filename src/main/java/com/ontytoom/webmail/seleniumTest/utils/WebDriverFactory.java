package com.ontytoom.webmail.seleniumTest.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by onTy on 2017-02-11.
 */
public class WebDriverFactory {


	public static WebDriver CreateDriver() {
		System.setProperty("webdriver.gecko.driver", "C:/dvt/Selenium/WebDrivers/geckodriver.exe");
		return new FirefoxDriver();
	}

}
