package com.ontytoom.webmail.seleniumTest.utils;

/**
 * Created by onTy on 2017-02-11.
 */
public class SleepUtil {

	public static final double SLEEP_DEFAULT = 1.0;


	public static void sleep( double sleepS ) {
		long s = (long)(sleepS * 1000);
		try{
			Thread.sleep(s);
		} catch ( InterruptedException ex ) {
		}
	}

	public static void sleep() {
		double s = Config.thinktimeS;
		sleep( s );
	}

}
