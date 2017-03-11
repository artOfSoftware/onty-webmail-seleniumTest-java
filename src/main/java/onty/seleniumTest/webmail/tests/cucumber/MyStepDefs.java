package onty.seleniumTest.webmail.tests.cucumber;

import cucumber.api.java8.En;

/**
 * Created by onTy on 2017-03-11.
 */


public class MyStepDefs implements En
{

	public MyStepDefs()
	{

//		Given("^I have (\\d+) cukes in my belly$", (Integer cukes) ->
//		{
//			System.out.format("Cukes: %n\n", cukes);
//		} );

		Given("^I am on the 'Login' page$", () ->
		{
			System.out.format( "I am on the login page\n" );
		} );

		And( "^I enter valid credentials$", () ->
		{
			System.out.format( "I enter valid credentials\n" );
		} );

		And( "^I enter invalid credentials$", () ->
		{
			System.out.format( "I enter invalid credentials\n" );
		} );

		Then( "^I should go to 'Accounts Home' page$", () ->
		{
			System.out.format( "I should go to 'Accounts Home' page\n" );
		} );

		Then( "^I should go to 'Login' page$", () ->
		{
			System.out.format( "I should go to 'Login' page\n" );
		} );



	}

}
