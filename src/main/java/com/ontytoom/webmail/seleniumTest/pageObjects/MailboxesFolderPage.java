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


public class MailboxesFolderPage extends APage
{

	@FindBy( css="div.app-main table tr" ) List<WebElement> rowsMessages;

	By locTextSubject     = By.xpath( "td[3]" );
	By locLinkReadMessage = By.xpath( "td[5]" );


	public MailboxesFolderPage( WebDriver driver ) throws WrongPageException
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! driver.getCurrentUrl().contains("mailboxes/folder") )
			throw new WrongPageException();

		init();
	}


	public boolean checkIsMessageListed( Message m )
	{
		return findMessage( m ) != null;
	}

	public MailboxesMessagePage clickReadMessage( Message m )
	{
		WebElement tr = findMessage( m );
		if ( tr == null )
			throw new TestException( "Cannot click message's Read link because message is not listed" );

		WebElement linkRead = tr.findElement( locLinkReadMessage );
		if ( linkRead == null )
			throw new TestException( "Cannot click message's Read link because cannot find the link, although found the message" );

		click( linkRead );

		waitForPageToLoad();
		return new MailboxesMessagePage( driver );
	}


	private WebElement findMessage( Message m )
	{
		WebElement found = null;
		for ( WebElement tr : rowsMessages )
		{
			WebElement td = tr.findElement( locTextSubject );
			String subject = td.getText();

			if ( areEqual( subject, m.subject ) )
			{
				found = tr;
				break;
			}
		}

		return found;
	}

}
