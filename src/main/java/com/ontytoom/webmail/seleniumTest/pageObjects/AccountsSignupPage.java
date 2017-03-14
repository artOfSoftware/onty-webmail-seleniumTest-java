package com.ontytoom.webmail.seleniumTest.pageObjects;

import com.ontytoom.webmail.seleniumTest.domainObjects.*;
import com.ontytoom.webmail.seleniumTest.exceptions.*;
import static com.ontytoom.webmail.seleniumTest.utils.StringUtil.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AccountsSignupPage extends APage
{

	@FindBy(id="signup_name")       WebElement fieldUserName;
	@FindBy(id="signup_fullname")   WebElement fieldFullName;
	@FindBy(id="signup_email")      WebElement fieldEmail;
	@FindBy(id="signup_password")   WebElement fieldPassword;
	@FindBy(id="signup_password_confirmation") WebElement fieldPassword2;
	@FindBy(id="signup")            WebElement buttonSignup;


	public AccountsSignupPage( WebDriver driver )
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! checkIfUrlContainsWait("accounts/signup") )
			throw new WrongPageException();

		if ( checkH1Contains( "Account Signup" ) )
		{
			String h1 = "";
			if ( textH1 != null && textH1.size() > 0 )
				h1 = textH1.get( 0 ).getText();
			throw new ValidationException( "H1 text is wrong on Signup page. got: " + h1 );
		}

		init();
	}


	public AccountsHomePage signupValid( User u )
	{
		type( fieldUserName,  u.name );
		type( fieldFullName,  u.fullName );
		type( fieldEmail,     u.email );
		type( fieldPassword,  u.password );
		type( fieldPassword2, u.password );
		click( buttonSignup );
		waitForPageToLoad();
		return new AccountsHomePage( driver );
	}

	public AccountsSignupPage signupInvalid( User u, String password2 )
	{
		if ( u.name != null )
			type( fieldUserName,  u.name );
		if ( u.fullName != null )
			type( fieldFullName,  u.fullName );
		if ( u.email != null )
			type( fieldEmail,     u.email );
		if ( u.password != null )
			type( fieldPassword,  u.password );
		if ( password2 != null )
			type( fieldPassword2, password2 );
		click( buttonSignup );
		waitForPageToLoad();
		return new AccountsSignupPage( driver );
	}



}
