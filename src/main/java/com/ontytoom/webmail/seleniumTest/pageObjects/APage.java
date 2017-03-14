package com.ontytoom.webmail.seleniumTest.pageObjects;

import com.ontytoom.webmail.seleniumTest.utils.*;
import com.ontytoom.webmail.seleniumTest.pageObjects.shared.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import java.util.*;


public class APage
{

	public WebDriver driver;

	// subpages
	public MenuSubpage    menu;
	public NoticesSubpage notices;


	// common elements
	@FindBy( css = "div.app-main > p" )
	protected List<WebElement> textParagraph;

	@FindBy( css = "div.app-main > h1" )
	protected List<WebElement> textH1;


	// ctor
	public APage( WebDriver driver )
	{
		this.driver = driver;
	}


	// helper methods

	protected void init()
	{
		PageFactory.initElements( driver, this );

		this.menu    = new MenuSubpage( this );
		this.notices = new NoticesSubpage( this );
	}

	public Class getCurrentPageType()
	{
		return this.getClass();
	}

	public static void waitForPageToLoad( double sleepS )
	{
		SleepUtil.sleep( sleepS );
	}
	
	public static void waitForPageToLoad()
	{
		SleepUtil.sleep();
	}


	// actions on locators
	public void click( WebElement e )
	{
		e.click();
	}

	public void type( WebElement e, CharSequence text )
	{
		e.sendKeys(text);
	}

	public WebElement find( By loc )
	{
		return driver.findElement( loc );
	}


	// methods to check element state

	public boolean isDisplayed( WebElement e )
	{
		return e.isDisplayed();
	}

	public boolean isDisplayedWait( By loc, long maxWaitS )
	{
		WebDriverWait wait = new WebDriverWait( this.driver, maxWaitS );
		try
		{
			wait.until( ExpectedConditions.visibilityOfElementLocated(loc) );
			return true;
		}
		catch ( TimeoutException ex )
		{
			return false;
		}
	}

	// validation methods
	public boolean isClickableWait( By loc, long maxWaitS )
	{
		WebDriverWait wait = new WebDriverWait( this.driver, maxWaitS );
		try
		{
			wait.until( ExpectedConditions.elementToBeClickable(loc) );
			return true;
		}
		catch ( TimeoutException ex )
		{
			return false;
		}
	}


	// validation methods

	public boolean checkIfUrlContainsWait( String expectedUrlContains, double timeoutS )
	{
		WebDriverWait wait = new WebDriverWait( driver, (long)timeoutS );
		try
		{
			wait.until( ExpectedConditions.urlContains(expectedUrlContains) );
			return true;
		}
		catch ( TimeoutException ex )
		{
			return false;
		}
	}

	public boolean checkIfUrlMatchesWait( String expectedUrlMatches, double timeoutS )
	{
		WebDriverWait wait = new WebDriverWait( driver, (long)timeoutS );
		try
		{
			wait.until(ExpectedConditions.urlMatches(expectedUrlMatches));
			return true;
		}
		catch ( TimeoutException ex )
		{
			return false;
		}
	}

}
