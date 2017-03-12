package onty.seleniumTest.webmail.pageObjects.shared;

import onty.seleniumTest.webmail.domainObjects.*;
import onty.seleniumTest.webmail.exceptions.*;
import onty.seleniumTest.webmail.pageObjects.*;

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

	// ctor
	public MenuSubpage( APage owner )
	{
		super( owner );
	}



	// actions on locators

	// public methods to click links
	public AccountsUsersPage clickUsers() throws WrongPageException
	{
		owner.click( menuAccountUsers );
		owner.waitForPageToLoad();
		return new AccountsUsersPage( owner.driver );
	}

	public AccountsLoginPage clickLogout() throws WrongPageException
	{
		owner.click( menuAccountLogout );
		owner.waitForPageToLoad();
		return new AccountsLoginPage( owner.driver );
	}

	public AccountsPasswordPage clickPassword() throws WrongPageException
	{
		owner.click(menuAccountPassword);
		owner.waitForPageToLoad();
		return new AccountsPasswordPage( owner.driver );
	}

	public MailboxesPage clickMailbox() throws WrongPageException
	{
		owner.click(menuMailbox);
		owner.waitForPageToLoad();
		return new MailboxesPage( owner.driver );
	}

	public MailboxesNewMessagePage clickNewMessage() throws WrongPageException
	{
		owner.click( menuMailboxNewMessage );
		owner.waitForPageToLoad();
		return new MailboxesNewMessagePage( owner.driver );
	}

	public MailboxesFoldersPage clickFolders() throws WrongPageException
	{
		owner.click(menuMailboxFolders);
		owner.waitForPageToLoad();
		return new MailboxesFoldersPage( owner.driver );
	}

	public MailboxesFolderPage clickFolder( Folder.Builtin folderType ) throws WrongPageException
	{
		if ( folderType == Folder.Builtin.Inbox )
			owner.click(menuMailboxFoldersInbox);
		else if ( folderType == Folder.Builtin.Sent )
			owner.click(menuMailboxFoldersSent);
		else if ( folderType == Folder.Builtin.Archived )
			owner.click(menuMailboxFoldersArchived);
		else
			throw new IllegalArgumentException("Unknown folder requested");

		owner.waitForPageToLoad();
		return new MailboxesFolderPage( owner.driver );
	}

	public MailboxesFolderPage clickFolder( Folder folder ) throws WrongPageException
	{
		WebElement e = menuMailboxFoldersAll.findElement( By.linkText( folder.name ) );
		owner.click(e);
		owner.waitForPageToLoad();
		return new MailboxesFolderPage( owner.driver );
	}


	// validation methods

	public boolean checkLoggedIn( User user )
	{
		String welcomeText = textWelcome.getText();
		String expectedWelcomeText = "Welcome, " + user.fullName + " (" + user.name + ")";

		if ( welcomeText.compareTo(expectedWelcomeText) != 0 )
			//throw new ValidationException("Welcome text validation failed");
			return false;

		return true;
	}

}
