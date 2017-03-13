package com.ontytoom.webmail.seleniumTest.tests.direct;

import com.ontytoom.webmail.seleniumTest.utils.*;

import org.junit.*;
import org.openqa.selenium.WebDriver;


public abstract class ATest
{

	protected WebDriver driver;


	@Before
	public void setup()
	{
		System.out.println("setup is running");
		Config.init();
		driver = WebDriverFactory.CreateDriver();
	}

	@After
	public void teardown()
	{
		System.out.println("teardown is running");
		driver.quit();
	}

}
