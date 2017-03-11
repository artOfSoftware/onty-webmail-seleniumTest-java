package onty.seleniumTest.webmail.utils;

import onty.seleniumTest.webmail.utils.*;

/**
 * Created by onTy on 2017-02-11.
 */
public class ResultsLogger {


	public static void performance( String componentName, String measurementName, double time, int nrErrors ) {
		System.out.print( "\"" + componentName + "\"," );
		System.out.print( "\"" + measurementName + "\"," );
		System.out.print( "\"" + time + "\"," );
		System.out.print( "\"" + nrErrors + "\"" );
		System.out.println();
	}

	public static void errorDetails( String componentName, String measurementName, Exception ex ) {
		System.out.print( "error: \"" + componentName + "\"," );
		System.out.print( "\"" + componentName + "\"," );
		System.out.print( "\"" + measurementName + "\"," );
		System.out.print( "\"" + ex.getMessage() + "\"" );
		System.out.println();
	}

}
