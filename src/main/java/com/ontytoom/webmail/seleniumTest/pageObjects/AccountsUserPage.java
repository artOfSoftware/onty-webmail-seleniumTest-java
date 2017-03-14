package com.ontytoom.webmail.seleniumTest.pageObjects;

import com.ontytoom.webmail.seleniumTest.exceptions.*;
import com.ontytoom.webmail.seleniumTest.domainObjects.*;

import static com.ontytoom.webmail.seleniumTest.utils.StringUtil.areEqual;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import java.util.*;


public class AccountsUserPage extends APage
{

	@FindBy( css = "div.app-main table tr td:nth-of-type(2)" )
	protected List<WebElement> textUserInfo;



	public AccountsUserPage( WebDriver driver ) throws WrongPageException
	{
		super(driver);
		
		// check url to make sure we are on the correct page
		if ( ! checkUrlMatchesWait( ".*/accounts/([\\d]+)/user" ) )
			throw new WrongPageException();

		init();
	}


	public boolean checkUserInfo( User u )
	{
		boolean okUserName, okEmail;

		okUserName = textUserInfo.size()>1 && areEqual( textUserInfo.get( 1 ).getText(), u.name );
		okEmail    = textUserInfo.size()>2 && areEqual( textUserInfo.get( 2 ).getText(), u.email );

		return okUserName && okEmail;
	}

}
