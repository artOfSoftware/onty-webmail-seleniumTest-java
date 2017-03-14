package com.ontytoom.webmail.seleniumTest.utils;

/**
 * Created by onTy on 2017-03-14.
 */
public class TestStateManagerFactory
{

	private static final ThreadLocal<TestStateManager> myManager = new ThreadLocal<TestStateManager>();

	public static TestStateManager getMyManager()
	{
		if ( myManager.get() == null )
			myManager.set( new TestStateManager("") );
		return myManager.get();
	}

	public static void deleteMyManager()
	{
		if ( myManager.get() != null )
		{
			myManager.get().finish();
			myManager.remove();
		}
	}

}
