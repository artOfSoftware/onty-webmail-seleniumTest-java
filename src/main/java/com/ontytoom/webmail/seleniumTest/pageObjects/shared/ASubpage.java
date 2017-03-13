package com.ontytoom.webmail.seleniumTest.pageObjects.shared;

import com.ontytoom.webmail.seleniumTest.pageObjects.APage;
import org.openqa.selenium.support.PageFactory;


public abstract class ASubpage
{

	protected APage owner;



	// ctor
	public ASubpage( APage owner )
	{
		if ( owner == null )
			throw new IllegalArgumentException( "owner must not be null" );

		this.owner = owner;

		init();
	}

	private void init()
	{
		PageFactory.initElements( owner.driver, this );
	}


}
