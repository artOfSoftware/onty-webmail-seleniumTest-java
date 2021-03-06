package com.ontytoom.webmail.seleniumTest.pageObjects;

import org.openqa.selenium.WebDriver;

import com.ontytoom.webmail.seleniumTest.exceptions.WrongPageException;


public class AccountsPasswordPage extends APage
{

//	public By locFieldUsername = By.id("user_name");
//	public By locFieldPassword = By.id("user_password");
//	public By locButtonLogin = By.id("login");


	public AccountsPasswordPage(WebDriver driver ) throws WrongPageException
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! checkUrlContainsWait("accounts/password") )
			throw new WrongPageException();

		init();
	}
	

}
