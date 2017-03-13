package com.ontytoom.webmail.seleniumTest.utils;

import com.ontytoom.webmail.seleniumTest.domainObjects.User;

import java.util.*;

/**
 * Created by onTy on 2017-03-12.
 */
public class TestData
{

	public static List<User> validUsers;
	public static List<User> invalidUsers;

	public static void init()
	{
		validUsers = new ArrayList<>();
		invalidUsers = new ArrayList<>();

		User u1 = new User();
		u1.name = "onty";
		u1.password = "toom";
		u1.fullName = "onty toom";
		u1.email = "onty@onty.us";

		validUsers.add( u1 );

		User u2 = new User();
		u2.name = "qwewqrew";
		u2.password = "qewrtgdfsgv";
		u2.fullName = "dsgfgdfgdf";
		u2.email = "khdfkjash@khklhlkjhkh.kjhkj";

		invalidUsers.add( u2 );
	}


}
