package onty.seleniumTest.webmail.tests;

import onty.seleniumTest.webmail.utils.*;

import org.junit.*;
import org.openqa.selenium.WebDriver;


public class ATest
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
