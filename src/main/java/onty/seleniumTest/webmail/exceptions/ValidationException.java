package onty.seleniumTest.webmail.exceptions;

public class ValidationException extends TestException
{

	public static final long serialVersionUID = 0L;

	public ValidationException()
	{
		super();
	}

	public ValidationException(String message)
	{
		super(message);
	}

	public ValidationException(Throwable cause)
	{
		super(cause);
	}

	public ValidationException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
