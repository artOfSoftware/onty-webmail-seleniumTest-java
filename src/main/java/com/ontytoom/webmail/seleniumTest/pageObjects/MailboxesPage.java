package com.ontytoom.webmail.seleniumTest.pageObjects;

import com.ontytoom.webmail.seleniumTest.exceptions.WrongPageException;
import org.openqa.selenium.WebDriver;


public class MailboxesPage extends APage
{


	public MailboxesPage(WebDriver driver ) throws WrongPageException
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! driver.getCurrentUrl().contains("mailboxes/index") )
			throw new WrongPageException();

		init();
	}
	

}
