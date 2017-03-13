package com.ontytoom.webmail.seleniumTest.pageObjects.shared;

import com.ontytoom.webmail.seleniumTest.domainObjects.*;
import com.ontytoom.webmail.seleniumTest.pageObjects.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;


public class MenuSubpage extends ASubpage
{

	// elements
	@FindBy( css = "a#logout" )                 	protected WebElement menuLogout;

	// locators for menu items for not logged-in user
	@FindBy( css = "a#nav-Login" )              	protected WebElement menuLogin;
	@FindBy( css = "a#nav-Signup" )             	protected WebElement menuSignup;

	// locators for menu items for logged-in user
	@FindBy( css = "a#nav-Accounts" )           	protected WebElement menuAccount;
	@FindBy( css = "a#nav-Accounts-password" )  	protected WebElement menuAccountPassword;
	@FindBy( css = "a#nav-Accounts-logout" )    	protected WebElement menuAccountLogout;
	@FindBy( css = "a#nav-Accounts-users" )     	protected WebElement menuAccountUsers;
	@FindBy( css = "a#nav-Mailboxes-index" )    	protected WebElement menuMailbox;
	@FindBy( css = "a#nav-Mailboxes-new-message" )	protected WebElement menuMailboxNewMessage;
	@FindBy( css = "a#nav-Mailboxes-folders" )  	protected WebElement menuMailboxFolders;

	@FindBy( css = "a[id^='nav-Mailboxes-folders-1']" )    protected WebElement menuMailboxFoldersAll;
	@FindBy( css = "a#nav-Mailboxes-folders-1" )    protected WebElement menuMailboxFoldersInbox;
	@FindBy( css = "a#nav-Mailboxes-folders-2" )	protected WebElement menuMailboxFoldersSent;
	@FindBy( css = "a#nav-Mailboxes-folders-3" )	protected WebElement menuMailboxFoldersArchived;

	@FindBy( css = "div.app-navigation-global p" )  protected WebElement textWelcome;
	@FindBy( css = "div.app-navigation-global" )    protected WebElement textWelcome2;


	// ctor
	public MenuSubpage( APage owner )
	{
		super( owner );
	}


	// public methods to click links
	public AccountsUsersPage clickUsers()
	{
		owner.click( menuAccountUsers );
		owner.waitForPageToLoad();
		return new AccountsUsersPage( owner.driver );
	}

	public AccountsLoginPage clickLogout()
	{
		owner.click( menuAccountLogout );
		owner.waitForPageToLoad();
		return new AccountsLoginPage( owner.driver );
	}

	public AccountsPasswordPage clickPassword()
	{
		owner.click(menuAccountPassword);
		owner.waitForPageToLoad();
		return new AccountsPasswordPage( owner.driver );
	}

	public MailboxesPage clickMailbox()
	{
		owner.click(menuMailbox);
		owner.waitForPageToLoad();
		return new MailboxesPage( owner.driver );
	}

	public MailboxesNewMessagePage clickNewMessage()
	{
		owner.click( menuMailboxNewMessage );
		owner.waitForPageToLoad();
		return new MailboxesNewMessagePage( owner.driver );
	}

	public MailboxesFoldersPage clickFolders()
	{
		owner.click( menuMailboxFolders );
		owner.waitForPageToLoad();
		return new MailboxesFoldersPage( owner.driver );
	}

	public MailboxesFolderPage clickFolder( Folder.Builtin folderType )
	{
		WebElement e;
		if ( folderType == Folder.Builtin.Inbox )
			e = menuMailboxFoldersInbox;
		else if ( folderType == Folder.Builtin.Sent )
			e = menuMailboxFoldersSent;
		else if ( folderType == Folder.Builtin.Archived )
			e = menuMailboxFoldersArchived;
		else
			throw new IllegalArgumentException("Unknown folder requested");

		owner.click( e );
		owner.waitForPageToLoad();
		return new MailboxesFolderPage( owner.driver );
	}

	public MailboxesFolderPage clickFolder( Folder folder )
	{
		WebElement e = menuMailboxFoldersAll.findElement( By.linkText( folder.name ) );
		owner.click( e );
		owner.waitForPageToLoad();
		return new MailboxesFolderPage( owner.driver );
	}


	// validation methods

	public boolean checkLoggedIn( User user )
	{
		String welcomeText = textWelcome.getText();
		String expectedWelcomeText = "Welcome, " + user.fullName + " (" + user.name + ")";

		return ( welcomeText.compareTo(expectedWelcomeText) == 0 );
	}

	public boolean checkLoggedOut()
	{
		String welcomeText = textWelcome2.getText();
		String expectedWelcomeText = "Please login or create an account.";

		return ( welcomeText.compareTo(expectedWelcomeText) == 0 );
	}

}
