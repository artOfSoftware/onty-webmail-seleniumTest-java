package com.ontytoom.webmail.seleniumTest.pageObjects;

import com.ontytoom.webmail.seleniumTest.domainObjects.Folder;
import com.ontytoom.webmail.seleniumTest.domainObjects.Message;
import com.ontytoom.webmail.seleniumTest.exceptions.TestException;
import com.ontytoom.webmail.seleniumTest.exceptions.WrongPageException;
import static com.ontytoom.webmail.seleniumTest.utils.StringUtil.areEqual;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class MailboxesMessagePage extends APage
{

	@FindBy( css="div.app-main table" )
	WebElement tableMessageDetails;

	By locLinkInFolder = By.xpath( ".//tr[2]/td[2]" );
	By locTextSubject  = By.xpath( ".//tr[5]/td[2]" );
	By locTextText     = By.xpath( ".//tr[7]/td[2]" );

	@FindBy( css="select#message_folder_id" )
	WebElement selectFolder;

	@FindBy( css="div.app-main input[name='commit']" )
	WebElement buttonMoveMessage;



	public MailboxesMessagePage( WebDriver driver )
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! checkUrlMatchesWait( ".*mailboxes/(\\d+)/message$") )
			throw new WrongPageException( "Expecting Mailboxes Message page" );

		init();
	}



	public void selectMoveToFolder( Folder folder )
	{
		Select select = new Select( selectFolder );
		select.selectByVisibleText( folder.name );
	}

	public MailboxesMessagePage clickMoveMessage()
	{
		click( buttonMoveMessage );

		waitForPageToLoad();
		return new MailboxesMessagePage( driver );
	}



	public boolean checkMessageDetails( Message m )
	{
		List<WebElement> tdSubject = tableMessageDetails.findElements( locTextSubject );
		List<WebElement> tdText    = tableMessageDetails.findElements( locTextText );

		if ( tdSubject.size() == 0 )
			throw new TestException( "Cannot determine the Message's subject from the page" );

		if ( tdText.size() == 0 )
			throw new TestException( "Cannot determine the Message's text from the page" );

		String subject = tdSubject.get(0).getText();
		String text    = tdText.get(0).getText();

		boolean okSubject = areEqual( subject, m.subject );
		boolean okText    = areEqual( text   , m.text    );

		return okSubject && okText;
	}

	public boolean checkInFolder( Folder folder )
	{
		List<WebElement> tdInFolder = tableMessageDetails.findElements( locLinkInFolder );

		if ( tdInFolder.size() == 0 )
			throw new TestException( "Cannot determine the Message's folder from the page" );

		String text = tdInFolder.get(0).getText();

		boolean isSame = text.contentEquals( folder.name );

		return isSame;
	}

}
