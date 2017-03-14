package com.ontytoom.webmail.seleniumTest.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.logging.Level;

/**
 * Created by onTy on 2017-02-11.
 */
public class WebDriverFactory
{


	public static WebDriver CreateDriver()
	{
		System.setProperty("webdriver.gecko.driver", "C:/dvt/Selenium/WebDrivers/geckodriver.exe");
		FirefoxOptions opt = new FirefoxOptions();
		opt.setLogLevel( Level.WARNING );
		return new FirefoxDriver( opt );
	}

}
