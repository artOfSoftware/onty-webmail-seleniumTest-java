package onty.seleniumTest.webmail.tests.cucumber;

import cucumber.api.java.en.*;

/**
 * Created by onTy on 2017-03-11.
 */


public class MyStepDefs
{

	public MyStepDefs()
	{
	}

	@Given("^I am on the 'Login' page$")
	public void gotoLoginPage()
	{
		System.out.format( "I am on the login page\n" );
	}

	@And( "^I enter valid credentials$")
	public void enterValidCredentials()
	{
		System.out.format( "I enter valid credentials\n" );
	}

	@And( "^I enter invalid credentials$")
	public void enterInvalidCredentials()
	{
		System.out.format( "I enter invalid credentials\n" );
	}

	@Then( "^I should go to 'Accounts Home' page$")
	public void checkAccountsHomePage()
	{
		System.out.format( "I should go to 'Accounts Home' page\n" );
	}

	@Then( "^I should go to 'Login' page$")
	public void checkLoginPage()
	{
		System.out.format( "I should go to 'Login' page\n" );
	}

	@Then( "^I should see error message$")
	public void checkErrorMessage()
	{
		System.out.format( "Checking for error message\n" );
	}


}
