package onty.seleniumTest.webmail.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by onTy on 2017-02-11.
 */
public class PerformanceTracker {

	private static final String componentName = "user1";

	private String transactionName;
	private HashMap<String,Action> actions;


	public PerformanceTracker( String transactionName ) {
		this.transactionName = transactionName;
		this.actions = new HashMap<String,Action>();
		this.actions.put("-", new Action("-"));
	}

	public void beginAction( String actionName ) {
		this.actions.put(actionName, new Action(actionName));
	}

	public void addError( Exception ex ) {
		actions.get("-").addError(ex);
	}

	public void addError( String actionName, Exception ex ) {
		actions.get(actionName).addError(ex);
	}

	public void completeAction( String actionName ) {
		String fullActionName = transactionName+"/"+actionName;
		Action a = actions.get(actionName);
		a.complete();
		ResultsLogger.performance(componentName, fullActionName, a.timeElapsed, a.errors.size() );
		for ( Exception ex : a.errors )
			ResultsLogger.errorDetails(componentName, fullActionName, ex);
	}

	public void completeTransaction() {
		String fullActionName = transactionName;
		Action a = actions.get("-");
		a.complete();
		ResultsLogger.performance(componentName, fullActionName, a.timeElapsed, a.errors.size() );
		for ( Exception ex : a.errors )
			ResultsLogger.errorDetails(componentName, fullActionName, ex);
	}


	public class Action {

		public String actionName;
		public Timer timer;
		public double timeElapsed = -1.0;
		public List<Exception> errors;

		public Action( String actionName ) {
			this.actionName = actionName;
			this.timer = new Timer();
			this.errors = new ArrayList<Exception>();
		}

		public void addError( Exception ex ) {
			this.errors.add(ex);
		}

		public void complete() {
			this.timeElapsed = this.timer.getElapsedS();
		}


	}

}
