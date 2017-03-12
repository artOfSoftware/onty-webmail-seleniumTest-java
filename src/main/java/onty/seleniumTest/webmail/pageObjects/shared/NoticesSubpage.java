package onty.seleniumTest.webmail.pageObjects.shared;

import onty.seleniumTest.webmail.domainObjects.Folder;
import onty.seleniumTest.webmail.domainObjects.User;
import onty.seleniumTest.webmail.exceptions.ValidationException;
import onty.seleniumTest.webmail.exceptions.WrongPageException;
import onty.seleniumTest.webmail.pageObjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


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

}
