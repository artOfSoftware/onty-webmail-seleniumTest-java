package onty.seleniumTest.webmail.pageObjects;

import onty.seleniumTest.webmail.utils.Config;
import org.openqa.selenium.*;

import onty.seleniumTest.webmail.exceptions.WrongPageException;


public class AccountsLoginPage extends APage {

	// locators
	private By locFieldUsername = By.id("user_name");
	private By locFieldPassword = By.id("user_password");
	private By locButtonLogin = By.id("login");
	
	// ctor
	public AccountsLoginPage( WebDriver driver ) throws WrongPageException {
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! driver.getCurrentUrl().contains("accounts/login") )
			throw new WrongPageException();
	}
	
	// method to go to entry page of AUT
	public static AccountsLoginPage Go( WebDriver driver ) throws WrongPageException {
		driver.navigate().to(Config.baseUrl);
		waitForPageToLoad();
		return new AccountsLoginPage(driver);
	}

	// login as valid user
	public AccountsHomePage loginAsValid( String user, String password )
		throws WrongPageException {
		driver.findElement(locFieldUsername).sendKeys(user);
		driver.findElement(locFieldPassword).sendKeys(password);
		driver.findElement(locButtonLogin).click();

		waitForPageToLoad();
		
		return new AccountsHomePage(driver);
	}
	
}
