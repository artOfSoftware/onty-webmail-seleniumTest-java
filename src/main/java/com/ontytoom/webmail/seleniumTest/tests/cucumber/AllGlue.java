package com.ontytoom.webmail.seleniumTest.tests.cucumber;

import com.ontytoom.webmail.seleniumTest.domainObjects.*;
import com.ontytoom.webmail.seleniumTest.exceptions.*;
import com.ontytoom.webmail.seleniumTest.pageObjects.*;
import com.ontytoom.webmail.seleniumTest.utils.*;
import static com.ontytoom.webmail.seleniumTest.utils.StringUtil.*;

import cucumber.api.*;
import cucumber.api.java.*;
import cucumber.api.java.en.*;
import gherkin.formatter.model.DataTableRow;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

/**
 * Created by onTy on 2017-03-11.
 */


public class AllGlue
{

	private TestStateManager manager = null;
	private Message          message = null;
	private Folder           folder  = null;


	// JUnit BeforeClass

	@org.junit.BeforeClass
	public static void setupAll()
	{
		Config.init();
	}

	// cucumber Before and After methods
	@cucumber.api.java.Before
	public void setup()
	{
		this.manager = TestStateManagerFactory.getMyManager();  //"cucumber tests"
	}

	@cucumber.api.java.After
	public void teardown()
	{
		this.manager.finish();
		TestStateManagerFactory.deleteMyManager();
	}

	// cucumber steps

	@Given( "^I am on the '(.+)' page$" )
	public void gotoInitialPage(String pageName)
	{
		manager.log.infoFormat( "I am on the '%s' page", pageName );

		if ( areEqualIgnoreCase( pageName, "login" ) )
		{
			AccountsLoginPage loginPage = AccountsLoginPage.Go( manager.driver );
			manager.setPage( loginPage );
			//currentPage = loginPage;
		}
		else
			throw new NotImplementedException();
	}

	@Given( "^I want to signup$" )
	public void gotoSignup()
	{
		manager.log.infoFormat( "I want to signup" );

		// only valid on login page
		//if ( !(currentPage instanceof AccountsLoginPage) )
		if ( ! manager.isPage( AccountsLoginPage.class ) )
			throw new WrongTestStateException( "Create account is only allowed from the Login page" );
		AccountsLoginPage loginPage = manager.getPage();

		AccountsSignupPage signupPage = loginPage.clickCreateAccount();
		//currentPage = signupPage;
		manager.setPage( signupPage );
	}

	@Given( "^I want to send a message$" )
	public void gotoSendMessage()
	{
		manager.log.infoFormat( "I want to send a message" );

		// only valid when user is logged in - dont yet have a way to check
//		if ( ! ( lastPage instanceof AccountsLoginPage ) )
//			throw new TestException( "Create account is only allowed from the login page" );

		APage anyPage = manager.getPage();

		MailboxesNewMessagePage newMessagePage = anyPage.menu.clickNewMessage();
		//currentPage = newMessagePage;
		manager.setPage( newMessagePage );
	}

	@When( "^I enter valid new account information:$" )
	public void signupValid(DataTable data)
	{
		manager.log.infoFormat( "I enter new account information: %s", ArrayUtils.toString( data.getGherkinRows().get( 1 ).getCells().toArray() ) );

		// only valid on signup page
		if ( ! manager.isPage( AccountsSignupPage.class ) )
		//	if ( !(currentPage instanceof AccountsSignupPage) )
			throw new WrongTestStateException( "Signup is only allowed from the Signup page" );

		//HashMap<String,String> map = data.getTableConverter().toMap( data, "".getClass(), "".getClass() );
		List<DataTableRow> rows = data.getGherkinRows();
		List<String> rowAsStrings = rows.get( 1 ).getCells();

		User u = new User();
		u.name = runMacros( rowAsStrings.get( 0 ) );
		u.fullName = runMacros( rowAsStrings.get( 1 ) );
		u.email = runMacros( rowAsStrings.get( 2 ) );
		u.password = runMacros( rowAsStrings.get( 3 ) );

		AccountsSignupPage signupPage = manager.getPage();

		AccountsHomePage homePage = signupPage.signupValid( u );

		//currentPage = homePage;
		manager.setPage( homePage );
	}

	@When( "^I enter valid new message information:$" )
	public void newMessageValid( DataTable data)
	{
		manager.log.infoFormat( "I enter valid new message info: %s", ArrayUtils.toString( data.getGherkinRows().get( 1 ).getCells().toArray() ) );

		// only valid on signup page
		if ( ! manager.isPage(  MailboxesNewMessagePage.class ) )
		//	if ( !(currentPage instanceof MailboxesNewMessagePage) )
			throw new WrongTestStateException( "Sending a message is only valid on New Message page" );

		List<DataTableRow> rows = data.getGherkinRows();
		List<String> rowAsStrings = rows.get( 1 ).getCells();

		String to      = runMacros( rowAsStrings.get( 0 ) );
		String subject = runMacros( rowAsStrings.get( 1 ) );
		String text    = runMacros( rowAsStrings.get( 2 ) );

		MailboxesNewMessagePage newMessagePage = manager.getPage();

		newMessagePage.selectTo( to );
		newMessagePage.enterSubjectAndText( subject, text );
		MailboxesPage mailboxPage = newMessagePage.clickSend();

		message = new Message();
		message.subject = subject;
		message.text = text;

		manager.setPage( mailboxPage );
	}

	@When( "^I enter valid username and (.+) password$" )
	public void loginValidUsername( String passwordType )
	{
		manager.log.infoFormat( "I enter valid username and %s password", passwordType );

		AccountsLoginPage loginPage = manager.getPage();

		if ( areEqualIgnoreCase( passwordType, "valid" ) )
		{
			User u = TestData.validUsers.get( 0 );
			AccountsHomePage homePage = loginPage.loginAsValid( u );
			manager.setPage( homePage );
		}
		else if ( areEqualIgnoreCase( passwordType, "invalid" ) )
		{
			User u = TestData.validUsers.get( 0 );
			u = u.clone();
			u.password = u.password + "_invalid";

			loginPage = loginPage.loginAsInvalid( u );
			manager.setPage( loginPage );
		}
		else
			throw new NotImplementedException();

	}

	@When( "^I enter invalid username$" )
	public void loginInvalidUsername()
	{
		manager.log.infoFormat( "I enter invalid username" );

		AccountsLoginPage loginPage = manager.getPage();

		User u = TestData.invalidUsers.get( 0 );
		loginPage = loginPage.loginAsInvalid( u );
		manager.setPage( loginPage );
	}

	@Then( "^I should find myself on '(.+)' page$" )
	public void checkCurrentPage( String pageName )
	{
		manager.log.infoFormat( "I should find myself on '%s' page", pageName );

		if ( areEqualIgnoreCase( pageName, "login" ) )
		{
			if ( !manager.isPage( AccountsLoginPage.class ) )
				throw new WrongPageException( "I should find myself on Login page ");   //, but am on " + currentPage.getClass().getName() );
		} else if ( areEqualIgnoreCase( pageName, "account" ) )
		{
			if ( !manager.isPage( AccountsHomePage.class ) )
				throw new WrongPageException( "I should find myself on Account page");   //, but am on " + currentPage.getClass().getName() );
		} else if ( areEqualIgnoreCase( pageName, "mailbox" ) )
		{
			if ( !manager.isPage( MailboxesPage.class ) )
				throw new WrongPageException( "I should find myself on Mailbox page");   //, but am on " + currentPage.getClass().getName() );
		} else if ( areEqualIgnoreCase( pageName, "folder" ) )
		{
			if ( !manager.isPage( MailboxesFolderPage.class ) )
				throw new WrongPageException( "I should find myself on Folder page");   //, but am on " + currentPage.getClass().getName() );
		} else if ( areEqualIgnoreCase( pageName, "message" ) )
		{
			if ( !manager.isPage( MailboxesMessagePage.class ) )
				throw new WrongPageException( "I should find myself on Message page");   //, but am on " + currentPage.getClass().getName() );
		} else
			throw new NotImplementedException();
	}

	@Then( "^I should see error message saying '(.+)'$" )
	public void checkErrorMessage(String errorMessage)
	{
		manager.log.infoFormat( "Checking for error message saying '%s'", errorMessage );

		APage anyPage = manager.getPage();

		if ( !anyPage.notices.checkErrorTextContains( errorMessage ) )
			throw new ValidationException( "Expected error text not found" );
	}

	@Then( "^I should see message saying '(.+)'$" )
	public void checkNoticeMessage(String noticeMessage)
	{
		manager.log.infoFormat( "Checking for notice message saying '%s'", noticeMessage );

		APage anyPage = manager.getPage();

		if ( !anyPage.notices.checkNoticeTextContains( noticeMessage ) )
			throw new ValidationException( "Expected error text not found" );
	}

	@When( "^I want to see the (.+) folder$" )
	public void gotoFolder(String folderName)
	{
		manager.log.infoFormat( "I want to see the %s folder", folderName );

		APage anyPage = manager.getPage();

		Folder.Builtin folder = Folder.Builtin.valueOf( folderName );
		MailboxesFolderPage folderPage = anyPage.menu.clickFolder( folder );

		manager.setPage( folderPage );
	}

	@Then( "^I should see message listed$" )
	public void checkIsMessageListedInFolder()
	{
		manager.log.infoFormat( "I should see message listed" );

		// only makes sense on Folder page
		if ( !manager.isPage( MailboxesFolderPage.class ) )
			throw new WrongTestStateException( "to check if Message is listed we must be on Folder page" );

		// make sure a previous step left us a message object
		if ( message == null )
			throw new TestException( "no Message object is available" );

		MailboxesFolderPage folderPage = manager.getPage();

		if ( !folderPage.checkIsMessageListed( message ) )
			throw new ValidationException( "message is not listed" );
	}


	@When( "^I want to see the message$" )
	public void gotoMessage()
	{
		manager.log.infoFormat( "I want to see the message" );

		// only makes sense on Folder page
		if ( !manager.isPage( MailboxesFolderPage.class ) )
			throw new WrongTestStateException( "to see the Message we must be on Folder page" );

		MailboxesFolderPage folderPage = manager.getPage();

		MailboxesMessagePage messagePage = folderPage.clickReadMessage( message );
		manager.setPage( messagePage );
	}

	@Then( "^I should see the details of the message$" )
	public void checkMessageDetails()
	{
		manager.log.infoFormat( "I should see the details of the message" );

		// only makes sense on Folder page
		if ( !manager.isPage( MailboxesMessagePage.class ) )
			throw new WrongTestStateException( "to check Message details we must be on Message page" );

		MailboxesMessagePage messagePage = manager.getPage();

		if ( ! messagePage.checkMessageDetails( message ) )
			throw new ValidationException( "Message details are wrong" );

	}

}
