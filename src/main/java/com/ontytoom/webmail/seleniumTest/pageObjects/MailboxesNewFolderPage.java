package com.ontytoom.webmail.seleniumTest.pageObjects;

import com.ontytoom.webmail.seleniumTest.exceptions.WrongPageException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MailboxesNewFolderPage extends APage
{

	@FindBy(css="input#folder_name")    WebElement fieldFolderName;
	@FindBy(css="input[name=commit]")   WebElement buttonCreateFolder;


	public MailboxesNewFolderPage( WebDriver driver )
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! checkUrlContainsWait("mailboxes/newfolder") )
			throw new WrongPageException();

		init();
	}


	public MailboxesFolderPage enterFolderInfoAndClickCreate( String name )
	{
		type( fieldFolderName, name );
		click( buttonCreateFolder );

		waitForPageToLoad();
		return new MailboxesFolderPage( driver );
	}


}
