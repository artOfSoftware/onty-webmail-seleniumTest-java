package onty.seleniumTest.webmail.tests.direct;

import onty.seleniumTest.webmail.domainObjects.*;
import onty.seleniumTest.webmail.exceptions.*;
import onty.seleniumTest.webmail.pageObjects.*;
import onty.seleniumTest.webmail.utils.*;

import org.junit.*;



public class LoginTests extends ATest
{

	@Test
	public void testLoginValid() throws WrongPageException
	{
		User user = TestData.validUsers.get( 0 );

		AccountsLoginPage login = null;
		login = AccountsLoginPage.Go( driver );

		AccountsHomePage home = null;
		home = login.loginAsValid( user );

		if ( ! home.menu.checkLoggedIn( user ) )
			throw new ValidationException( "Menu welcome text is wrong" );

		login = home.menu.clickLogout();

		if ( ! login.menu.checkLoggedOut() )
			throw new ValidationException( "Login page menu welcome text is wrong" );
	}

	@Test
	public void testLoginInvalid() throws WrongPageException
	{
		User user = TestData.invalidUsers.get( 0 );

		AccountsLoginPage login = null;
		login = AccountsLoginPage.Go( driver );

		login = login.loginAsInvalid( user );

		if ( ! login.menu.checkLoggedOut() )
			throw new ValidationException( "Menu welcome text is wrong" );

		if ( ! login.notices.checkErrorText( "Login unsuccessful" ) )
			throw new ValidationException( "Notice about unsuccessful login is missing" );

	}

}
