package com.ontytoom.webmail.seleniumTest.unitTests;

import com.ontytoom.webmail.seleniumTest.utils.StringUtil;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by onTy on 2017-03-13.
 */
public class StringUtilTests
{

	@Test
	public void testAreEqual()
	{
		assertTrue ( StringUtil.areEqual( null, null ) );
		assertFalse( StringUtil.areEqual( "", null ) );
		assertFalse( StringUtil.areEqual( null, "" ) );
		assertTrue ( StringUtil.areEqual( "", "" ) );

		assertTrue ( StringUtil.areEqual( "abc", "abc" ) );
		assertFalse( StringUtil.areEqual( "abc", "Abc" ) );
	}

	@Test
	public void testAreEqualIgnoreCase()
	{
		assertTrue ( StringUtil.areEqualIgnoreCase( null, null ) );
		assertFalse( StringUtil.areEqualIgnoreCase( "", null ) );
		assertFalse( StringUtil.areEqualIgnoreCase( null, "" ) );
		assertTrue ( StringUtil.areEqualIgnoreCase( "", "" ) );

		assertTrue ( StringUtil.areEqualIgnoreCase( "abc", "abc" ) );
		assertTrue ( StringUtil.areEqualIgnoreCase( "abc", "Abc" ) );
	}

	@Test
	public void testMakeRandomString()
	{
		for ( int len=1; len<20; len++ )
		{
			String s = StringUtil.makeRandomString( len );
			assertTrue( s.length() == len );
			for( int i=0; i<s.length(); i++ )
				assertTrue( s.charAt( i ) >= 'a' && s.charAt( i ) <= 'z' );
		}
	}

}
