package com.ontytoom.webmail.seleniumTest.tests.cucumber;

import com.ontytoom.webmail.seleniumTest.domainObjects.*;
import com.ontytoom.webmail.seleniumTest.exceptions.*;
import com.ontytoom.webmail.seleniumTest.pageObjects.*;
import com.ontytoom.webmail.seleniumTest.utils.*;
import static com.ontytoom.webmail.seleniumTest.utils.StringUtil.*;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.*;
import cucumber.api.java.en.*;
import gherkin.formatter.model.DataTableRow;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by onTy on 2017-03-11.
 */


public class AccountSteps
{

	private TestStateManager manager;

	private AccountsLoginPage  loginPage;
	private AccountsSignupPage signupPage;
	private AccountsHomePage   homePage;
	private APage              lastPage;


	// ctor and dtor

	public AccountSteps()
	{
	}

	// cucumber Before and After methods

	@Before
	public void setup()
	{
		this.manager = TestStateManagerFactory.getMyManager();  //"cucumber tests"
	}

	@After
	public void teardown()
	{
		this.manager.finish();
		TestStateManagerFactory.deleteMyManager();
	}

	// cucumber steps

	@Given( "^I am on the '(.+)' page$" )
	public void gotoPage( String pageName )
	{
		manager.log.infoFormat( "I am on the '%s' page", pageName );

		if ( areEqualIgnoreCase( pageName, "login" ) )
		{
			loginPage = AccountsLoginPage.Go( manager.driver );
			lastPage = loginPage;
		}
		else
			throw new NotImplementedException();
	}

	@Given( "^I want to signup$" )
	public void gotoSignup()
	{
		manager.log.infoFormat( "I want to signup" );

		// only valid on login page
		if ( ! ( lastPage instanceof AccountsLoginPage ) )
			throw new TestException( "Create account is only allowed from the login page" );

		signupPage = loginPage.clickCreateAccount();
		lastPage = signupPage;
	}

	@When( "^I enter valid new account information:$")
	public void signupValid( DataTable data )
	{
		manager.log.infoFormat( "I enter new account information: %s", ArrayUtils.toString( data.getGherkinRows().get(0) ) );

		// only valid on signup page
		if ( ! ( lastPage instanceof AccountsSignupPage ) )
			throw new TestException( "Signup is only allowed from the Signup page" );

		//HashMap<String,String> map = data.getTableConverter().toMap( data, "".getClass(), "".getClass() );
		List<DataTableRow> rows = data.getGherkinRows();
		List<String> rowAsStrings = rows.get( 1 ).getCells();

		User u = new User();
		u.name     = runMacros( rowAsStrings.get( 0 ) );
		u.fullName = runMacros( rowAsStrings.get( 1 ) );
		u.email    = runMacros( rowAsStrings.get( 2 ) );
		u.password = runMacros( rowAsStrings.get( 3 ) );

		homePage = signupPage.signupValid( u );
		lastPage = homePage;
	}

	@When( "^I enter (.+) credentials$")
	public void login( String credentialsType )
	{
		manager.log.infoFormat( "I enter %s credentials", credentialsType );

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

	@Then( "^I should find myself on '(.+)' page$")
	public void checkCurrentPage( String pageName )
	{
		manager.log.infoFormat( "I should find myself on '%s' page", pageName );

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

	@Then( "^I should see error message saying '(.+)'$")
	public void checkErrorMessage( String errorMessage )
	{
		manager.log.infoFormat( "Checking for error message saying '%s'", errorMessage );

		if ( ! lastPage.notices.checkErrorTextContains( errorMessage ) )
			throw new ValidationException( "Expected error text not found" );
	}

	@Then( "^I should see message saying '(.+)'$")
	public void checkNoticeMessage( String noticeMessage )
	{
		manager.log.infoFormat( "Checking for notice message saying '%s'", noticeMessage );

		if ( ! lastPage.notices.checkNoticeTextContains( noticeMessage ) )
			throw new ValidationException( "Expected error text not found" );
	}

}
