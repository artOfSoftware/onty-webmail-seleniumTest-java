package com.ontytoom.webmail.seleniumTest.pageObjects;

import com.ontytoom.webmail.seleniumTest.domainObjects.Message;
import com.ontytoom.webmail.seleniumTest.exceptions.TestException;
import com.ontytoom.webmail.seleniumTest.exceptions.WrongPageException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.ontytoom.webmail.seleniumTest.utils.StringUtil.areEqual;


public class MailboxesMessagePage extends APage
{

	@FindBy( css="div.app-main table" )
	WebElement tableMessageDetails;

	By locTextSubject = By.xpath( "tr[5]/td[2]" );
	By locTextText    = By.xpath( "td[7]/td[2]" );


	public MailboxesMessagePage( WebDriver driver ) throws WrongPageException
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! driver.getCurrentUrl().contains("mailboxes/message") )
			throw new WrongPageException();

		init();
	}


	public boolean checkMessageDetails( Message m )
	{
		WebElement tdSubject = tableMessageDetails.findElement( locTextSubject );
		WebElement tdText    = tableMessageDetails.findElement( locTextText );

		if ( tdSubject == null )
			throw new TestException( "Cannot determine the Message's subject from the page" );

		if ( tdText == null )
			throw new TestException( "Cannot determine the Message's text from the page" );

		String subject = tdSubject.getText();
		String text    = tdText.getText();

		boolean okSubject = areEqual( subject, m.subject );
		boolean okText    = areEqual( text   , m.text    );

		return okSubject && okText;
	}

}
