package com.ontytoom.webmail.seleniumTest.tests.direct;

import com.ontytoom.webmail.seleniumTest.domainObjects.User;
import com.ontytoom.webmail.seleniumTest.pageObjects.*;
import com.ontytoom.webmail.seleniumTest.utils.*;

import org.junit.*;
import static org.junit.Assert.*;


public class AccountTests extends ATest
{

	private AccountsLoginPage    login    = null;
	private AccountsHomePage     home     = null;
	private AccountsUsersPage    users    = null;
	private AccountsUserPage     user     = null;
	private AccountsPasswordPage password = null;

	@Test
	public void testAccountUsers()
	{
		User u = TestData.validUsers.get( 0 );
		login = AccountsLoginPage.Go( driver );

		home = login.loginAsValid( u );

		users = home.menu.clickUsers();

		assertTrue( users.checkUserIsListed( u ) );

		user = users.clickLinkUser( u );

		assertTrue( user.checkUserInfo( u ) );
	}

	@Test
	public void testAccountPassword()
	{
		User u = TestData.validUsers.get( 0 );
		login = AccountsLoginPage.Go( driver );

		home = login.loginAsValid( u );

		password = home.menu.clickPassword();
	}

	@Test
	public void testAccountLogout()
	{
		User u = TestData.validUsers.get( 0 );
		login = AccountsLoginPage.Go( driver );

		home = login.loginAsValid( u );

		home.menu.clickLogout();
	}


}
