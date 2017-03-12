package onty.seleniumTest.webmail.pageObjects;

import onty.seleniumTest.webmail.exceptions.WrongPageException;
import org.openqa.selenium.WebDriver;


public class MailboxesNewFolderPage extends APage
{

//	public By locFieldUsername = By.id("user_name");
//	public By locFieldPassword = By.id("user_password");
//	public By locButtonLogin = By.id("login");


	public MailboxesNewFolderPage( WebDriver driver ) throws WrongPageException
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! driver.getCurrentUrl().contains("mailboxes/newfolder") )
			throw new WrongPageException();

		init();
	}
	

}
