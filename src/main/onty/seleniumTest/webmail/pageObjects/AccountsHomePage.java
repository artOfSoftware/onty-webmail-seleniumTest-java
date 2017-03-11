package onty.seleniumTest.webmail.pageObjects;

import org.openqa.selenium.*;

import onty.seleniumTest.webmail.exceptions.WrongPageException;


public class AccountsHomePage extends APage {

//	public By locFieldUsername = By.id("user_name");
//	public By locFieldPassword = By.id("user_password");
//	public By locButtonLogin = By.id("login");
	
	
	public AccountsHomePage( WebDriver driver ) throws WrongPageException {
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! driver.getCurrentUrl().contains("accounts/home") )
			throw new WrongPageException();
	}
	
//	public accountsHomePage loginAsValid( String user, String password ) {
//		driver.findElement(locFieldUsername).sendKeys(user);
//		driver.findElement(locFieldPassword).sendKeys(user);
//		driver.findElement(locButtonLogin).click();
//		
//		waitForPageToLoad();
//		
//		return new accountsHomePage(driver);
//		
//	}
	
}
