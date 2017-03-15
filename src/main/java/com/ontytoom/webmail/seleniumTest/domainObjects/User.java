package com.ontytoom.webmail.seleniumTest.domainObjects;

/**
 * Created by onTy on 2017-02-11.
 */
public class User implements Cloneable
{

	// instance variables
	public int id;
	public String name;
	public String password;
	public String fullName;
	public String email;


	@Override
	public User clone()
	{
		User u2 = new User();
		u2.id = this.id;
		u2.name = this.name;
		u2.password = this.password;
		u2.fullName = this.fullName;
		u2.email = this.email;

		return u2;
	}

}
