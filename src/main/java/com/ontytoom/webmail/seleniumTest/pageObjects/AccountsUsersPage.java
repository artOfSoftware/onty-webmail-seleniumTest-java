package com.ontytoom.webmail.seleniumTest.pageObjects;

import com.ontytoom.webmail.seleniumTest.domainObjects.*;
import com.ontytoom.webmail.seleniumTest.exceptions.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import java.util.*;


public class AccountsUsersPage extends APage
{

	@FindBy( css = "div.app-main ul a[id^='show-account-']" )
	List<WebElement> linksUser;

	public AccountsUsersPage( WebDriver driver )
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! driver.getCurrentUrl().contains("accounts/users") )
			throw new WrongPageException();

		init();
	}
	

	public AccountsUserPage clickLinkUser( User u )
	{
		WebElement e = findUserInList( u );
		if ( e == null )
			throw new TestException("User " + u.name + " is not in the list, so cannot click");

		click( e );
		waitForPageToLoad();
		return new AccountsUserPage( driver );
	}

	public boolean checkUserIsListed( User u )
	{
		return findUserInList( u ) != null;
	}


	private WebElement findUserInList( User u )
	{
		WebElement found = null;
		for ( WebElement e : linksUser )
			if ( e.getText().equalsIgnoreCase( u.name ) )
			{
				found = e;
				break;
			}
		return found;
	}

}
