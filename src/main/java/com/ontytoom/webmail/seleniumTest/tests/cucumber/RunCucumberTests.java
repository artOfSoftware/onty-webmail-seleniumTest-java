package com.ontytoom.webmail.seleniumTest.tests.cucumber;

import com.ontytoom.webmail.seleniumTest.utils.Config;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith( Cucumber.class )
@CucumberOptions(
	plugin = { "pretty", "html:target/cucumber" },
	features = { "classpath:features" }
	)
public class RunCucumberTests
{
}
