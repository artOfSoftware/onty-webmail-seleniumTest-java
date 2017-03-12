package onty.seleniumTest.webmail.tests;

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
			throw new ValidationException( "Menu welcome text does not match expected" );
	}

}
