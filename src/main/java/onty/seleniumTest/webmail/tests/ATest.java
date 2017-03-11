package onty.seleniumTest.webmail.tests;

import org.junit.*;
import org.openqa.selenium.WebDriver;

import onty.seleniumTest.webmail.utils.*;


public class ATest
{

	protected WebDriver driver;


	@Before
	public void setup() {
		System.out.println("setup is running");
		Config.ReadConfig();
		driver = WebDriverFactory.CreateDriver();
	}

	@After
	public void teardown() {
		System.out.println("teardown is running");
		driver.quit();
	}

}
