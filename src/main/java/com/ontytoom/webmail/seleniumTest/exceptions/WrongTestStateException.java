package com.ontytoom.webmail.seleniumTest.exceptions;

public class WrongTestStateException extends TestException
{

	public static final long serialVersionUID = 0L;

	public WrongTestStateException()
	{
		super();
	}

	public WrongTestStateException(String message)
	{
		super(message);
	}

	public WrongTestStateException(Throwable cause)
	{
		super(cause);
	}

	public WrongTestStateException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public WrongTestStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
