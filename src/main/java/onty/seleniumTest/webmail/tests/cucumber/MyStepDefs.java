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

	@Given("^I am on the '(.*)' page$")
	public void gotoLoginPage( String pageName )
	{
		log( "I am on the '%s' page", pageName );
	}

	@And( "^I enter valid credentials$")
	public void enterValidCredentials()
	{
		log( "I enter valid credentials" );
	}

	@And( "^I enter invalid credentials$")
	public void enterInvalidCredentials()
	{
		log( "I enter invalid credentials" );
	}

	@Then( "^I should go to '(.*)' page$")
	public void checkAccountsHomePage( String pageName )
	{
		log( "I should go to '%s' page", pageName );
	}

	@Then( "^I should see error message$")
	public void checkErrorMessage()
	{
		log( "Checking for error message" );
	}


	private void log( String format, String... args )
	{
		System.out.print( "### " );
		System.out.format( format, (Object[]) args );
		System.out.println();
	}


}
