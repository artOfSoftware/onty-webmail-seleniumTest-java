package com.ontytoom.webmail.seleniumTest.utils;

import java.util.Date;

/**
 * Created by onTy on 2017-02-11.
 */
public class Timer {

	private long timeStarted;


	public Timer() {
		timeStarted = getTimestamp();
	}

	public double getElapsedS() {
		long elapsedMs = getTimestamp()-timeStarted;
		double elapsedS = ((double)(elapsedMs))/1000.0;
		return elapsedS;
	}


	// private methods
	private static long getTimestamp() {
		return (new Date()).getTime();
	}

}
