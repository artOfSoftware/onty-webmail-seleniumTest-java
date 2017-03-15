package com.ontytoom.webmail.seleniumTest.utils;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Properties;

/**
 * Created by onTy on 2017-02-11.
 */
public class Config
{

	public static String baseUrl = "https://onty-webmail-ruby.herokuapp.com/";
	public static double thinktimeS = 1.0;
	public static double timeoutS = 60.0;
	public static String instanceName = "default_instance_name";

	public static String browser = "chrome";
	public static String firefoxDriverPath = "C:/dvt/Selenium/WebDrivers/geckodriver.exe";
	public static String chromeDriverPath = "C:/dvt/Selenium/WebDrivers/chromedriver.exe";

	private static boolean isInitialized = false;
	private static final Object sync = new Object();


	public static void init()
	{
		if ( isInitialized )
			return;

		synchronized ( sync )
		{
			if ( isInitialized )
				return;

			Properties prop = new Properties();

			try
			{
				FileInputStream fis = new FileInputStream( "config.xml" );
				prop.loadFromXML( fis );

				if ( prop.containsKey( "baseUrl" ) )
					baseUrl = prop.getProperty( "baseUrl" );

				if ( prop.containsKey( "thinktimeS" ) )
					thinktimeS = Double.parseDouble( prop.getProperty( "thinktimeS" ) );

				if ( prop.containsKey( "timeoutS" ) )
					timeoutS = Double.parseDouble( prop.getProperty( "timeoutS" ) );

				if ( prop.containsKey( "instanceName" ) )
					instanceName = prop.getProperty( "instanceName" );

				if ( prop.containsKey( "browser" ) )
					browser = prop.getProperty( "browser" );

				if ( prop.containsKey( "firefoxDriverPath" ) )
					firefoxDriverPath = prop.getProperty( "firefoxDriverPath" );

				if ( prop.containsKey( "chromeDriverPath" ) )
					chromeDriverPath = prop.getProperty( "chromeDriverPath" );

			}
			catch ( Exception ex )
			{
				ex.printStackTrace();
				System.err.println( "Count not read the config.xml file." );
			}

			printConfig( System.out );

			TestData.init();

			isInitialized = true;
		}

	}

	public static void printConfig( PrintStream ps )
	{
		ps.println( "Config:" );
		ps.format( "baseUrl = %s\n", baseUrl );
		ps.format( "thinktimeS= %f\n", thinktimeS );
		ps.format( "timeoutS = %f\n", timeoutS );
		ps.format( "instanceName = %s\n", instanceName );
		ps.format( "browser = %s\n", browser );
		ps.format( "firefoxDriverPath = %s\n", firefoxDriverPath );
		ps.format( "chromeDriverPath = %s\n", chromeDriverPath );
	}

}
