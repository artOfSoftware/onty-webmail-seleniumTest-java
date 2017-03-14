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


public class AccountSteps
{

	private TestStateManager manager;
	private APage currentPage;

	private AccountsLoginPage loginPage;
	private AccountsSignupPage signupPage;
	private AccountsHomePage homePage;
	private MailboxesPage mailboxPage;
	private MailboxesNewMessagePage newMessagePage;
	private MailboxesFolderPage folderPage;
	private MailboxesMessagePage messagePage;

	private Message message;

	// ctor and dtor

//	public AccountSteps()
//	{
//	}

	// cucumber Before and After methods

	@Before
	public void setup()
	{
		this.manager = TestStateManagerFactory.getMyManager();  //"cucumber tests"
	}

	@After
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
			loginPage = AccountsLoginPage.Go( manager.driver );
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
		if ( !(currentPage instanceof AccountsLoginPage) )
			throw new WrongTestStateException( "Create account is only allowed from the Login page" );

		signupPage = loginPage.clickCreateAccount();
		currentPage = signupPage;
	}

	@Given( "^I want to send a message$" )
	public void gotoSendMessage()
	{
		manager.log.infoFormat( "I want to send a message" );

		// only valid when user is logged in - dont yet have a way to check
//		if ( ! ( lastPage instanceof AccountsLoginPage ) )
//			throw new TestException( "Create account is only allowed from the login page" );

		newMessagePage = currentPage.menu.clickNewMessage();
		currentPage = newMessagePage;
	}

	@When( "^I enter valid new account information:$" )
	public void signupValid(DataTable data)
	{
		manager.log.infoFormat( "I enter new account information: %s", ArrayUtils.toString( data.getGherkinRows().get( 1 ).getCells().toArray() ) );

		// only valid on signup page
		if ( !(currentPage instanceof AccountsSignupPage) )
			throw new WrongTestStateException( "Signup is only allowed from the Signup page" );

		//HashMap<String,String> map = data.getTableConverter().toMap( data, "".getClass(), "".getClass() );
		List<DataTableRow> rows = data.getGherkinRows();
		List<String> rowAsStrings = rows.get( 1 ).getCells();

		User u = new User();
		u.name = runMacros( rowAsStrings.get( 0 ) );
		u.fullName = runMacros( rowAsStrings.get( 1 ) );
		u.email = runMacros( rowAsStrings.get( 2 ) );
		u.password = runMacros( rowAsStrings.get( 3 ) );

		homePage = signupPage.signupValid( u );
		currentPage = homePage;
	}

	@When( "^I enter valid new message information:$" )
	public void newMessageValid(DataTable data)
	{
		manager.log.infoFormat( "I enter valid new message info: %s", ArrayUtils.toString( data.getGherkinRows().get( 1 ).getCells().toArray() ) );

		// only valid on signup page
		if ( !(currentPage instanceof MailboxesNewMessagePage) )
			throw new WrongTestStateException( "Sending a message is only valid on New Message page" );

		List<DataTableRow> rows = data.getGherkinRows();
		List<String> rowAsStrings = rows.get( 1 ).getCells();

		String to = runMacros( rowAsStrings.get( 0 ) );
		String subject = runMacros( rowAsStrings.get( 1 ) );
		String text = runMacros( rowAsStrings.get( 2 ) );

		newMessagePage.selectTo( to );
		newMessagePage.enterSubjectAndText( subject, text );
		mailboxPage = newMessagePage.clickSend();

		message = new Message();
		message.subject = subject;
		message.text = text;

		currentPage = mailboxPage;
	}

	@When( "^I enter (.+) credentials$" )
	public void login(String credentialsType)
	{
		manager.log.infoFormat( "I enter %s credentials", credentialsType );

		if ( areEqualIgnoreCase( credentialsType, "valid" ) )
		{
			User u = TestData.validUsers.get( 0 );
			homePage = loginPage.loginAsValid( u );
			currentPage = homePage;
		} else if ( areEqualIgnoreCase( credentialsType, "invalid" ) )
		{
			User u = TestData.invalidUsers.get( 0 );
			loginPage = loginPage.loginAsInvalid( u );
			currentPage = loginPage;
		} else
			throw new NotImplementedException();

	}

	@Then( "^I should find myself on '(.+)' page$" )
	public void checkCurrentPage(String pageName)
	{
		manager.log.infoFormat( "I should find myself on '%s' page", pageName );

		if ( areEqualIgnoreCase( pageName, "login" ) )
		{
			if ( !(currentPage instanceof AccountsLoginPage) )
				throw new WrongPageException( "I should find myself on Login page, but am on " + currentPage.getClass().getName() );
		} else if ( areEqualIgnoreCase( pageName, "account" ) )
		{
			if ( !(currentPage instanceof AccountsHomePage) )
				throw new WrongPageException( "I should find myself on Account page, but am on " + currentPage.getClass().getName() );
		} else if ( areEqualIgnoreCase( pageName, "mailbox" ) )
		{
			if ( !(currentPage instanceof MailboxesPage) )
				throw new WrongPageException( "I should find myself on Mailbox page, but am on " + currentPage.getClass().getName() );
		} else if ( areEqualIgnoreCase( pageName, "folder" ) )
		{
			if ( !(currentPage instanceof MailboxesFolderPage) )
				throw new WrongPageException( "I should find myself on Folder page, but am on " + currentPage.getClass().getName() );
		} else if ( areEqualIgnoreCase( pageName, "message" ) )
		{
			if ( !(currentPage instanceof MailboxesMessagePage) )
				throw new WrongPageException( "I should find myself on Message page, but am on " + currentPage.getClass().getName() );
		} else
			throw new NotImplementedException();
	}

	@Then( "^I should see error message saying '(.+)'$" )
	public void checkErrorMessage(String errorMessage)
	{
		manager.log.infoFormat( "Checking for error message saying '%s'", errorMessage );

		if ( !currentPage.notices.checkErrorTextContains( errorMessage ) )
			throw new ValidationException( "Expected error text not found" );
	}

	@Then( "^I should see message saying '(.+)'$" )
	public void checkNoticeMessage(String noticeMessage)
	{
		manager.log.infoFormat( "Checking for notice message saying '%s'", noticeMessage );

		if ( !currentPage.notices.checkNoticeTextContains( noticeMessage ) )
			throw new ValidationException( "Expected error text not found" );
	}

	@When( "^I want to see the (.+) folder$" )
	public void gotoFolder(String folderName)
	{
		manager.log.infoFormat( "I want to see the %s folder", folderName );

		Folder.Builtin folder = Folder.Builtin.valueOf( folderName );
		folderPage = currentPage.menu.clickFolder( folder );

		currentPage = folderPage;
	}

	@Then( "^I should see message listed$" )
	public void checkIsMessageListedInFolder()
	{
		manager.log.infoFormat( "I should see message listed" );

		// only makes sense on Folder page
		if ( !(currentPage instanceof MailboxesFolderPage) )
			throw new WrongTestStateException( "to check if Message is listed we must be on Folder page" );

		// make sure a previous step left us a message object
		if ( message == null )
			throw new TestException( "no Message object is available" );

		if ( !folderPage.checkIsMessageListed( message ) )
			throw new ValidationException( "message is not listed" );
	}


	@When( "^I want to see the message$" )
	public void gotoMessage()
	{
		manager.log.infoFormat( "I want to see the message" );

		// only makes sense on Folder page
		if ( !(currentPage instanceof MailboxesFolderPage) )
			throw new WrongTestStateException( "to see the Message we must be on Folder page" );

		messagePage = folderPage.clickReadMessage( message );
		currentPage = messagePage;
	}

	@Then( "^I should see the details of the message$" )
	public void checkMessageDetails()
	{
		manager.log.infoFormat( "I should see the details of the message" );

		// only makes sense on Folder page
		if ( !(currentPage instanceof MailboxesMessagePage) )
			throw new WrongTestStateException( "to check Message details we must be on Message page" );

		if ( ! messagePage.checkMessageDetails( message ) )
			throw new ValidationException( "Message details are wrong" );

	}

}