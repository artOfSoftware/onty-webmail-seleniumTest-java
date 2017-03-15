package com.ontytoom.webmail.seleniumTest.utils;

import static com.ontytoom.webmail.seleniumTest.utils.StringUtil.areEqualIgnoreCase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.chrome.*;
import java.util.logging.Level;

/**
 * Created by onTy on 2017-02-11.
 */

public class WebDriverFactory
{


	public static WebDriver CreateDriver()
	{
		WebDriver driver = null;

		if ( areEqualIgnoreCase( Config.browser, "firefox" ) )
		{
			System.setProperty( "webdriver.gecko.driver", Config.firefoxDriverPath );
			FirefoxOptions opt = new FirefoxOptions();
			opt.setLogLevel( Level.WARNING );
			driver = new FirefoxDriver( opt );
		}
		else if ( areEqualIgnoreCase( Config.browser, "chrome" ) )
		{
			System.setProperty( "webdriver.chrome.driver", Config.chromeDriverPath );
			driver = new ChromeDriver();
		}
		else
			throw new IllegalArgumentException( "Don't know how to make driver for browser=" + Config.browser );

		return driver;
	}

}
