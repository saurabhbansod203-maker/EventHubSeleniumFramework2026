package com.eventHub.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "@target/failed_scenarios.txt", // Reads only failed scenario paths
        glue = {"com.eventhub.stepdefinitions", "com.eventhub.hooks"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/rerun-report.html"
        }
)


public class FailedTestRunner extends AbstractTestNGCucumberTests {

}
