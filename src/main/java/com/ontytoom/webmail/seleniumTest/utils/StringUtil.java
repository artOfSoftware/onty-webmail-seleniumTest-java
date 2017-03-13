package com.ontytoom.webmail.seleniumTest.utils;

/**
 * Created by onTy on 2017-03-12.
 */
public class StringUtil
{

	public static boolean areEqual( String s1, String s2 )
	{
		if ( s1 == null || s2 == null )
			return ( s1 == null && s2 == null );

		return s1.compareTo( s2 ) == 0;
	}

	public static boolean areEqualIgnoreCase( String s1, String s2 )
	{
		if ( s1 == null || s2 == null )
			return ( s1 == null && s2 == null );

		return s1.compareToIgnoreCase( s2 ) == 0;
	}

	public static String makeRandomString( int length )
	{
		if ( length < 1 || length > 1024 )
			throw new IllegalArgumentException( "length must be [1,1024]" );

		StringBuffer sb = new StringBuffer(  );
		for ( int i = 0; i < length; i++ )
		{
			char c = (char)MathUtil.makeRandomInt( (int)'a', (int)'z' );
			sb.append( c );
		}
		return sb.toString();
	}


}
