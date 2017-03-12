package onty.seleniumTest.webmail.tests;

import onty.seleniumTest.webmail.exceptions.*;
import onty.seleniumTest.webmail.pageObjects.*;

import org.junit.*;



public class LoginTests extends ATest
{

	@Test
	public void testLoginValid() throws WrongPageException
	{
		String username = "onty";
		String password = "toom";

		AccountsLoginPage loginPage = null;
		loginPage = AccountsLoginPage.Go( driver );

		AccountsHomePage homePage = null;
		homePage = loginPage.loginAsValid(username, password);
	}

}
