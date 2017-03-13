package com.ontytoom.webmail.seleniumTest.tests.direct;

import com.ontytoom.webmail.seleniumTest.domainObjects.*;
import com.ontytoom.webmail.seleniumTest.exceptions.*;
import com.ontytoom.webmail.seleniumTest.pageObjects.*;
import com.ontytoom.webmail.seleniumTest.utils.*;

import org.junit.*;
import static org.junit.Assert.*;


public class LoginTests extends ATest
{

	private AccountsLoginPage login = null;
	private AccountsHomePage  home  = null;

	@Test
	public void testLoginValid()
	{
		User user = TestData.validUsers.get( 0 );

		login = AccountsLoginPage.Go( driver );

		home = login.loginAsValid( user );

		if ( ! home.menu.checkLoggedIn( user ) )
			throw new ValidationException( "Menu welcome text is wrong" );

		login = home.menu.clickLogout();

		if ( ! login.menu.checkLoggedOut() )
			throw new ValidationException( "Login page menu welcome text is wrong" );
	}

	@Test
	public void testLoginInvalid()
	{
		User user = TestData.invalidUsers.get( 0 );

		login = AccountsLoginPage.Go( driver );

		login = login.loginAsInvalid( user );

		if ( ! login.menu.checkLoggedOut() )
			throw new ValidationException( "Menu welcome text is wrong" );

		if ( ! login.checkMessagesInvalidLogin() )
			throw new ValidationException( "Notice about unsuccessful login is missing" );

	}

}
