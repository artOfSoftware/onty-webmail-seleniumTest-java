package onty.seleniumTest.webmail.pageObjects;

import onty.seleniumTest.webmail.utils.Config;
import org.openqa.selenium.*;

import onty.seleniumTest.webmail.exceptions.WrongPageException;
import org.openqa.selenium.support.FindBy;


public class AccountsLoginPage extends APage
{

	// elements
	@FindBy( id = "user_name" ) 	private WebElement fieldUsername;
	@FindBy( id = "user_password" )	private WebElement fieldPassword;
	@FindBy( id = "login" )     	private WebElement buttonLogin;
	
	// ctor
	public AccountsLoginPage( WebDriver driver ) throws WrongPageException
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! driver.getCurrentUrl().contains("accounts/login") )
			throw new WrongPageException();

		// init element
		init();
	}
	
	// method to go to entry page of AUT
	public static AccountsLoginPage Go( WebDriver driver ) throws WrongPageException
	{
		driver.get( Config.baseUrl );
		waitForPageToLoad();
		return new AccountsLoginPage( driver );
	}

	// login as valid user
	public AccountsHomePage loginAsValid( String username, String password ) throws WrongPageException
	{
		type( fieldUsername, username );
		type( fieldPassword, password );
		click( buttonLogin );

		waitForPageToLoad();
		
		return new AccountsHomePage( driver );
	}
	
}
