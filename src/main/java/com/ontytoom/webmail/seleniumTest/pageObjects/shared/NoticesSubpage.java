package com.ontytoom.webmail.seleniumTest.pageObjects.shared;

import com.ontytoom.webmail.seleniumTest.pageObjects.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NoticesSubpage extends ASubpage
{


	@FindBy( css = "div.error" )
	protected WebElement messageError;

	@FindBy( css = "div.notice" )
	protected WebElement messageNotice;



	// ctor
	public NoticesSubpage( APage owner )
	{
		super( owner );
	}

	public boolean checkNoticePresent()
	{
		return messageNotice != null;
	}

	public boolean checkNoticeText( String expectedText )
	{
		if ( !checkNoticePresent() )
			return false;

		String text = messageNotice.getText();
		return text.compareTo( expectedText ) == 0;
	}

	public boolean checkErrorPresent()
	{
		return messageError != null;
	}

	public boolean checkErrorText( String expectedText )
	{
		if ( !checkErrorPresent() )
			return false;

		String text = messageError.getText();
		return text.compareTo( "Error: " + expectedText ) == 0;
	}

	public boolean checkErrorTextContains( String expectedTextSubstring )
	{
		if ( !checkErrorPresent() )
			return false;

		String text = messageError.getText().toLowerCase();
		return text.contains( expectedTextSubstring.toLowerCase() );
	}

}
