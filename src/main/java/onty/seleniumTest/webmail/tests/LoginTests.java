package onty.seleniumTest.webmail.tests;

import onty.seleniumTest.webmail.exceptions.WrongPageException;
import onty.seleniumTest.webmail.utils.PerformanceTracker;
import org.junit.*;

import onty.seleniumTest.webmail.pageObjects.*;


public class LoginTests extends ATest {

	@Test
	public void testLoginValid() throws WrongPageException
	{
		String username = "onty";
		String password = "toom";
		PerformanceTracker pt = new PerformanceTracker("testLoginValid");

		String actionName = "go to login page";
		pt.beginAction(actionName);
		AccountsLoginPage loginPage = null;
		loginPage = AccountsLoginPage.Go(driver);
		pt.completeAction(actionName);

		actionName = "login as valid user";
		pt.beginAction(actionName);
		AccountsHomePage homePage = null;
		homePage = loginPage.loginAsValid(username, password);
		pt.completeAction(actionName);

		pt.completeTransaction();
	}

}
