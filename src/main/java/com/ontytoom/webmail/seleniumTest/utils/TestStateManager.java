package com.ontytoom.webmail.seleniumTest.utils;

import com.ontytoom.webmail.seleniumTest.exceptions.WrongTestStateException;
import com.ontytoom.webmail.seleniumTest.pageObjects.APage;
import org.openqa.selenium.WebDriver;

/**
 * Created by onTy on 2017-03-13.
 */
public class TestStateManager
{

	public WebDriver driver;
	public String contextName;
	public MyLogger log;

	private APage currentPage;

	public TestStateManager( String contextName )
	{
		this.contextName = contextName;
		this.log = new MyLogger( contextName );
		this.currentPage = null;
		Config.init();
		createDriver();
	}

	// current page management
	public void setPage( APage page )
	{
		this.currentPage = page;
	}

	public <T extends APage> T getPage()
	{
//		if ( ! ( currentPage instanceof T ) )
//			throw new WrongTestStateException( "requestor is asking for wrong type of page, which means there is a mismatch of steps in scenario" );
		return (T)currentPage;
	}

	public boolean isPage(Class c )
	{
		return c.isInstance( currentPage );
	}


	// driver management methods

	public void finish()
	{
		finalize();
	}

	@Override
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
