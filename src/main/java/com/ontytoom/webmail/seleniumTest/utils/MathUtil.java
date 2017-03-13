package com.ontytoom.webmail.seleniumTest.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by onTy on 2017-03-12.
 */
public class MathUtil
{

	/**
	 *
	 * @param min
	 * @param max
	 * @return random integer in range [min,max] both boundaries included;
	 */
	public static int makeRandomInt( int min, int max )
	{
		if ( min > max )
			throw new IllegalArgumentException( "min must be <= max" );
		return ThreadLocalRandom.current().nextInt( (max-min+1) ) + min;
	}

}
