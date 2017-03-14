package com.ontytoom.webmail.seleniumTest.tests.cucumber;

import com.ontytoom.webmail.seleniumTest.domainObjects.*;
import com.ontytoom.webmail.seleniumTest.exceptions.*;
import com.ontytoom.webmail.seleniumTest.pageObjects.*;
import com.ontytoom.webmail.seleniumTest.utils.*;
import static com.ontytoom.webmail.seleniumTest.utils.StringUtil.*;

import cucumber.api.java.*;
import cucumber.api.java.en.*;
import org.openqa.selenium.*;

/**
 * Created by onTy on 2017-03-11.
 */


public class AccountSteps
{

	private TestStateManager manager;
	private WebDriver driver;

	private AccountsLoginPage loginPage;
	private AccountsHomePage  homePage;
	private APage             lastPage;


	// ctor and dtor

	public AccountSteps()
	{
	}

	// cucumber Before and After methods

	@Before
	public void setup()
	{
		this.manager = new TestStateManager( "cucumber tests" );
		this.driver  = this.manager.driver;
	}

	@After
	public void teardown()
	{
		this.manager.finish();
	}

	// cucumber steps

	@Given("^I am on the '(.+)' page$")
	public void gotoPage( String pageName )
	{
		log( "I am on the '%s' page", pageName );

		if ( areEqualIgnoreCase( pageName, "login" ) )
		{
			loginPage = AccountsLoginPage.Go(driver);
			lastPage = loginPage;
		}
		else
			throw new NotImplementedException();
	}

	@And( "^I enter (.+) credentials$")
	public void enterCredentials( String credentialsType )
	{
		log( "I enter %s credentials", credentialsType );

		if ( areEqualIgnoreCase( credentialsType, "valid" ) )
		{
			User u = TestData.validUsers.get(0);
			homePage = loginPage.loginAsValid( u );
			lastPage = homePage;
		}
		else if ( areEqualIgnoreCase( credentialsType, "invalid" ) )
		{
			User u = TestData.invalidUsers.get(0);
			loginPage = loginPage.loginAsInvalid( u );
			lastPage = loginPage;
		}
		else
			throw new NotImplementedException();

	}

	@Then( "^I should find myself on '(.*)' page$")
	public void checkCurrentPage( String pageName )
	{
		log( "I should find myself on '%s' page", pageName );

		if ( areEqualIgnoreCase( pageName, "login" ) )
		{
			if ( ! ( lastPage instanceof AccountsLoginPage ) )
				throw new WrongPageException();
		}
		else if ( areEqualIgnoreCase( pageName, "accounts home" ) )
		{
			if ( ! ( lastPage instanceof AccountsHomePage ) )
				throw new WrongPageException();
		}
		else
			throw new NotImplementedException();
	}

	@Then( "^I should see error message saying '(.*)'$")
	public void checkErrorMessage( String errorMessage )
	{
		log( "Checking for error message saying '%s'", errorMessage );

		if ( ! lastPage.notices.checkErrorTextContains( errorMessage ) )
			throw new ValidationException( "Expected error text not found" );
	}


	private void log( String format, String... args )
	{
		System.out.print( "### " );
		System.out.format( format, (Object[]) args );
		System.out.println();
	}


}
