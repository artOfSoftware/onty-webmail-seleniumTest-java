package com.ontytoom.webmail.seleniumTest.pageObjects;

import com.ontytoom.webmail.seleniumTest.domainObjects.User;
import com.ontytoom.webmail.seleniumTest.utils.Config;
import org.openqa.selenium.*;

import com.ontytoom.webmail.seleniumTest.exceptions.WrongPageException;
import org.openqa.selenium.support.FindBy;


public class AccountsLoginPage extends APage
{

	// elements
	@FindBy( id = "user_name" ) 	private WebElement fieldUsername;
	@FindBy( id = "user_password" )	private WebElement fieldPassword;
	@FindBy( id = "login" )     	private WebElement buttonLogin;
	@FindBy( id = "signup" )     	private WebElement linkSignup;


	// ctor
	public AccountsLoginPage( WebDriver driver )
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! driver.getCurrentUrl().contains("accounts/login") )
			throw new WrongPageException();

		// init element
		init();
	}
	
	// method to go to entry page of AUT
	public static AccountsLoginPage Go( WebDriver driver )
	{
		driver.get( Config.baseUrl );
		waitForPageToLoad();
		return new AccountsLoginPage( driver );
	}

	// login as valid user
	public AccountsHomePage loginAsValid( User user )
	{
		type( fieldUsername, user.name );
		type( fieldPassword, user.password );
		click( buttonLogin );

		waitForPageToLoad();
		
		return new AccountsHomePage( driver );
	}

	// login as invalid user
	public AccountsLoginPage loginAsInvalid( User user )
	{
		type( fieldUsername, user.name );
		type( fieldPassword, user.password );
		click( buttonLogin );

		waitForPageToLoad();

		return new AccountsLoginPage( driver );
	}

	// create account
	public AccountsSignupPage clickCreateAccount()
	{
		click( linkSignup );
		waitForPageToLoad();
		return new AccountsSignupPage( driver );
	}

	// validation

	public boolean checkMessagesInvalidLogin()
	{
		return notices.checkErrorText( "Login unsuccessful" );
	}

}
