package com.ontytoom.webmail.seleniumTest.utils;

/**
 * Created by onTy on 2017-03-14.
 */
public class MyLogger
{

	private String label;

	public MyLogger( String label )
	{
		this.label = label;
	}

	public void info( String msg )
	{
		System.out.format( "### [%s] %s", label, msg );
		System.out.println();
	}

	public void infoFormat( String format, Object... data )
	{
		System.out.format( "### [%s] ", label );
		System.out.format( format, data );
		System.out.println();
	}

}
