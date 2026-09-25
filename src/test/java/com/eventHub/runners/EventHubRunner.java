package com.eventHub.runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * EventHubRunner configures Cucumber scenario discovery, reporting plugins,
 * glue bindings, and enables parallel scenario execution using TestNG.
 */

@CucumberOptions(
		
		 // Relative path to feature files (Works on Windows, Mac, Linux, and CI/CD)
        features = "src/test/resources/eventhubfeatures",
        
     // Packages containing Step Definitions and ApplicationHooks
        glue = {"com.eventHub.stepdefinitions", "com.eventHub.Hooks"},
        
     // Execution filter tags (Overridable via Maven command line: -Dcucumber.filter.tags="@smoke")
        tags = "@smoke or @regression",
        
     // Reporting Plugins: Console, HTML, JSON (Jenkins), and Rerun logger
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-html-report.html",
                "json:target/cucumber-reports/cucumber.json",
                "rerun:target/failed_scenarios.txt"
        },
	
     // Clean console output without unreadable ANSI escape codes
        monochrome = false,
		
     // Set to true to verify step bindings without executing browser actions
        dryRun = false
        
        
		)

public class EventHubRunner extends AbstractTestNGCucumberTests {

	/**
     * Overrides TestNG DataProvider to enable parallel scenario execution.
     * Thread count is controlled dynamically via testng.xml or pom.xml.
     */
	@Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
	
	
	}
	
	
}
