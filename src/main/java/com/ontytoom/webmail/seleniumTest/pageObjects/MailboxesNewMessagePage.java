package com.ontytoom.webmail.seleniumTest.pageObjects;

import com.ontytoom.webmail.seleniumTest.exceptions.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.*;
import java.util.List;


public class MailboxesNewMessagePage extends APage
{

	@FindBy(id="message_to_id")     WebElement dropdownTo;
	@FindBy(id="message_subject")   WebElement fieldSubject;
	@FindBy(id="message_text")      WebElement fieldText;
	@FindBy(css="input#send")       WebElement buttonSend;


	public MailboxesNewMessagePage(WebDriver driver ) throws WrongPageException
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! checkUrlContainsWait("mailboxes/new") )
			throw new WrongPageException("Expecting New Message page");

		init();

		if ( ! checkH1Contains( "New Message" ) )
			throw new ValidationException( "H1 text is wrong for New Message page" );
	}


	public void selectTo( String substr )
	{
		Select select = new Select( dropdownTo );
		List<WebElement> es = select.getOptions();

		WebElement found = null;
		for ( WebElement e : es )
			if ( e.getText().contains( substr ) )
			{
				found = e;
				break;
			}

		if ( found == null )
			throw new TestException( "No such item was found in To dropdown" );

		select.selectByValue( found.getAttribute( "value" ) );
	}

	public void enterSubjectAndText( String subject, String text )
	{
		if ( subject != null )
			type( fieldSubject, subject );
		if ( text != null )
			type( fieldText, text );
	}

	public MailboxesPage clickSend()
	{
		click( buttonSend );
		waitForPageToLoad();
		return new MailboxesPage( driver );
	}

}
