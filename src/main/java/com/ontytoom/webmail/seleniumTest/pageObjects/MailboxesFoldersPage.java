package com.ontytoom.webmail.seleniumTest.pageObjects;

import com.ontytoom.webmail.seleniumTest.exceptions.WrongPageException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MailboxesFoldersPage extends APage
{

	@FindBy(css="a#create-folder")  WebElement linkCreateFolder;

	public MailboxesFoldersPage(WebDriver driver ) throws WrongPageException
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! checkUrlContainsWait("mailboxes/folders") )
			throw new WrongPageException();

		init();
	}


	public MailboxesNewFolderPage clickCreateFolder()
	{
		click( linkCreateFolder );

		waitForPageToLoad();
		return new MailboxesNewFolderPage( driver );
	}


}
