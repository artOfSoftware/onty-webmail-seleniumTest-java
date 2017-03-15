package com.ontytoom.webmail.seleniumTest.tests.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith( Cucumber.class )
@CucumberOptions(
	plugin = { "pretty", "html:target/cucumber" },
	features = { "classpath:features/Send Message.feature" }
	)
public class RunSomeCucumberTests
{
}


