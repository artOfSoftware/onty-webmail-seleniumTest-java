package onty.seleniumTest.webmail.exceptions;

public class TestException extends RuntimeException
{

	public static final long serialVersionUID = 0L;

	public TestException()
	{
		super();
	}

	public TestException(String message)
	{
		super(message);
	}

	public TestException(Throwable cause)
	{
		super(cause);
	}

	public TestException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public TestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
