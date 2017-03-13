package com.ontytoom.webmail.seleniumTest.unitTests;

import com.ontytoom.webmail.seleniumTest.utils.MathUtil;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by onTy on 2017-03-13.
 */
public class MathUtilTests
{

	@Test
	public void testMakeRandomInt()
	{
		for ( int i = -1000; i < 1000; i++ )
		{
			int min = -1000;
			int max = i;

			int n = MathUtil.makeRandomInt( min, max );
			assertTrue( n >= min );
			assertTrue( n <= max );
		}
	}

}
