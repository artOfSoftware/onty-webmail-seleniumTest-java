package onty.seleniumTest.webmail.domainObjects;

/**
 * Created by onTy on 2017-02-11.
 */
public class Folder
{

	// built-in folders
	public enum Builtin
	{
		Inbox,
		Sent,
		Archived
	}

	// instance variables
	public int id;
	public String name;

}
