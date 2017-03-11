package onty.seleniumTest.webmail.pageObjects;

import onty.seleniumTest.webmail.exceptions.ValidationException;
import onty.seleniumTest.webmail.exceptions.WrongPageException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import onty.seleniumTest.webmail.domainObjects.*;
import onty.seleniumTest.webmail.utils.*;


public class APage {

	protected WebDriver driver; 

	// locators
	protected static final By locFirstParagraph = By.cssSelector("div.app-main > p");
	protected static final By locFirstH1Text = By.cssSelector("div.app-main > h1");
	protected static final By locMessageError = By.cssSelector("div.error");
	protected static final By locMessageNotice = By.cssSelector("div.notice");
	protected static final By locLinkLogout = By.cssSelector("a#logout");

	// locators for menu items for not logged-in user
	protected static final By locMenuLogin = By.cssSelector("a#nav-Login");
	protected static final By locMenuSignup = By.cssSelector("a#nav-Signup");

	// locators for menu items for not logged-in user
	protected static final By locMenuAccount = By.cssSelector("a#nav-Accounts");
	protected static final By locMenuAccountPassword = By.cssSelector("a#nav-Accounts-password");
	protected static final By locMenuAccountLogout = By.cssSelector("a#nav-Accounts-logout");
	protected static final By locMenuAccountUsers = By.cssSelector("a#nav-Accounts-users");
	protected static final By locMenuMailbox = By.cssSelector("a#nav-Mailboxes-index");
	protected static final By locMenuMailboxNewmessage = By.cssSelector("a#nav-Mailboxes-new-message");
	protected static final By locMenuMailboxFolders = By.cssSelector("a#nav-Mailboxes-folders");
	protected static final By locMenuMailboxFoldersInbox = By.cssSelector("a#nav-Mailboxes-folders-1");
	protected static final By locMenuMailboxFoldersSent = By.cssSelector("a#nav-Mailboxes-folders-2");
	protected static final By locMenuMailboxFoldersArchived = By.cssSelector("a#nav-Mailboxes-folders-3");


	// ctor
	public APage( WebDriver driver ) {
		this.driver = driver;
	}


	// helper methods

	protected static void waitForPageToLoad( double sleepS ) {
		SleepUtil.sleep(sleepS);
	}
	
	protected static void waitForPageToLoad() {
		SleepUtil.sleep();
	}


	// actions on locators
	protected void click( By loc ) {
		driver.findElement(loc).click();
	}

	protected void type( By loc, String text ) {
		driver.findElement(loc).sendKeys(text);
	}

	protected WebElement find( By loc ) {
		return driver.findElement(loc);
	}

	// validation methods
	protected boolean isVisible( By loc, long maxWaitS )
	{
//		try {
			WebDriverWait wait = new WebDriverWait( this.driver, maxWaitS );
			WebElement element = wait.until( ExpectedConditions.visibilityOfElementLocated(loc) );
//			if ( element == null )
//				return false;
			return true;
//		}
//		catch ( WebDriverTimeoutException ex ) {
//			return false;
//		}
	}

	// validation methods
	protected boolean isClickable( By loc, long maxWaitS )
	{
		WebDriverWait wait = new WebDriverWait( this.driver, maxWaitS );
		try {
			wait.until( ExpectedConditions.elementToBeClickable(loc) );
		} catch ( TimeoutException ex ) {
			return false;
		}
		return true;
	}


	// public methods to click links
	public AccountsUsersPage clickMenuUsers() throws WrongPageException {
		click(locMenuAccountUsers);
		waitForPageToLoad();
		return new AccountsUsersPage(driver);
	}

	public AccountsLoginPage clickMenuLogout() throws WrongPageException {
		click(locMenuAccountLogout);
		waitForPageToLoad();
		return new AccountsLoginPage(driver);
	}

	public AccountsPasswordPage clickMenuPassword() throws WrongPageException {
		click(locMenuAccountPassword);
		waitForPageToLoad();
		return new AccountsPasswordPage(driver);
	}

	public MailboxesPage clickMenuMailbox() throws WrongPageException {
		click(locMenuMailbox);
		waitForPageToLoad();
		return new MailboxesPage(driver);
	}

	public MailboxesNewmessagePage clickMenuNewmessage() throws WrongPageException {
		click(locMenuMailboxNewmessage);
		waitForPageToLoad();
		return new MailboxesNewmessagePage(driver);
	}

	public MailboxesFoldersPage clickMenuFolders() throws WrongPageException {
		click(locMenuMailboxFolders);
		waitForPageToLoad();
		return new MailboxesFoldersPage(driver);
	}

	public MailboxesFolderPage clickMenuFolder( Folder.Builtin folderType ) throws WrongPageException {
		if ( folderType == Folder.Builtin.Inbox )
			click(locMenuMailboxFoldersInbox);
		else if ( folderType == Folder.Builtin.Sent )
			click(locMenuMailboxFoldersSent);
		else if ( folderType == Folder.Builtin.Archived )
			click(locMenuMailboxFoldersArchived);
		else
			throw new IllegalArgumentException("Unknown folder requested");

		waitForPageToLoad();
		return new MailboxesFolderPage(driver);
	}

	public MailboxesFolderPage clickMenuFolder( Folder folder ) throws WrongPageException {
		click(By.linkText(folder.name));
		waitForPageToLoad();
		return new MailboxesFolderPage(driver);
	}


	// validation methods

	public boolean checkMenuLoggedin( User user ) throws ValidationException {
		String welcomeText = find(By.cssSelector( "div.app-navigation-global p") ).getText();
		String expectedWelcomeText = "Welcome, " + user.fullName + " (" + user.name + ")";

		if ( welcomeText.compareTo(expectedWelcomeText) != 0 )
			//throw new ValidationException("Welcome text validation failed");
			return false;

		return true;
	}

	protected boolean checkIfUrlContains( String expectedUrlContains, long timeoutS ) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutS );

		try {
			wait.until(ExpectedConditions.urlContains(expectedUrlContains));
		} catch ( TimeoutException ex ) {
			return false;
		}
		return true;
	}

	protected boolean checkIfUrlMatches( String expectedUrlMatches, long timeoutS ) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutS );

		try {
			wait.until(ExpectedConditions.urlMatches(expectedUrlMatches));
		} catch ( TimeoutException ex ) {
			return false;
		}
		return true;
	}

}
