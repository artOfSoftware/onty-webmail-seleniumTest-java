package com.ontytoom.webmail.seleniumTest.pageObjects;

import org.openqa.selenium.*;

import com.ontytoom.webmail.seleniumTest.exceptions.WrongPageException;


public class AccountsHomePage extends APage
{

	
	public AccountsHomePage( WebDriver driver ) throws WrongPageException
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! driver.getCurrentUrl().contains("accounts/home") )
			throw new WrongPageException();

		init();
	}
	

}
