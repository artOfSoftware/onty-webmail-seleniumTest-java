package com.ontytoom.webmail.seleniumTest.utils;

import org.openqa.selenium.WebDriver;

/**
 * Created by onTy on 2017-03-13.
 */
public class TestStateManager
{

	public WebDriver driver;
	public String contextName;
	public MyLogger log;

	public TestStateManager( String contextName )
	{
		this.contextName = contextName;
		this.log = new MyLogger( contextName );
		Config.init();
		createDriver();
	}

	public void finish()
	{
		finalize();
	}

	public void finalize()
	{
		deleteDriver();
	}

	private void createDriver()
	{
		this.driver = WebDriverFactory.CreateDriver();
	}

	private void deleteDriver()
	{
		if ( this.driver != null )
			this.driver.quit();
		this.driver = null;
	}

}
