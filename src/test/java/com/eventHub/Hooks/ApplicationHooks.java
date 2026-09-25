package com.eventHub.Hooks;

import org.openqa.selenium.WebDriver;

import com.eventHub.drivermanager.DriverFactory;
import com.eventHub.utils.ConfigReader;
import com.eventHub.utils.LogUtils;
import com.eventHub.utils.ScreenshotUtils;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

/**
 * ApplicationHooks controls the lifecycle of Cucumber test scenarios. It
 * initializes thread-safe driver instances before scenarios and handles
 * screenshot capture and memory cleanup upon scenario completion.
 */

public class ApplicationHooks {

	private WebDriver driver;

	/**
	 * Executes before every Cucumber scenario. Reads dynamic execution parameters
	 * and launches an isolated browser session.
	 */

	@Before(order = 0)
	public void setup(Scenario scenario) {
		
		//System.out.println("LOG [INFO]: Starting Scenario -> " + scenario.getName());

		LogUtils.startScenario(scenario.getName());
		
		// 1. Fetch browser choice from config or CLI override (-Dbrowser=firefox)

		String browserName = ConfigReader.getProperty("browser"); // Pass key Value
		String appUrl = ConfigReader.getProperty("url");

		// 2. Initialize ThreadLocal driver for current worker thread

		driver = DriverFactory.initDriver(browserName);

		// 3. Navigate to base URL

		driver.get(appUrl);

	}

	/**
	 * Executes immediately after scenario completion (Order 1 runs BEFORE Order 0).
	 * Takes screenshot evidence if the scenario fails.
	 */
	// scenario = object acts as a data container holding real-time information
	// about the specific test execution.

	@After(order=1)
	public void captureFailureEvidence(Scenario scenario) {

		// WHY CHECK IF FAILED:
		// Attaching screenshots only for failed scenarios keeps report size lightweight
		// while providing immediate visual failure diagnostics for CI/CD runs.

		if (scenario.isFailed()) {

			System.err.println("LOG [ERROR]: Scenario Failed -> " + scenario.getName());

			// Capture screenshot as byte array using our utility

			byte[] screenshot = ScreenshotUtils.captureScreenshotAsBytes(driver);

			// Attach screenshot directly into Cucumber HTML / Extent Report

			scenario.attach(screenshot, "image/png", "Failed_Step_Screenshot");

		}

	}

	/**
	 * Executes as the final teardown step (Order 0 runs LAST in @After hooks).
	 * Ensures browser processes are closed and ThreadLocal memory references are
	 * purged.
	 */
//	@After(order=0)
//	public void tearDown(Scenario scenario) {
//
//		System.out.println(
//				"LOG [INFO]: Terminating Scenario -> " + scenario.getName() + " | Status: " + scenario.getStatus());
  //    LogUtils.endScenario(scenario.getName());
//		// WHY QUIT DRIVER HERE:
//		// Prevents orphaned browser processes (chromedriver.exe) from lingering in
//		// background memory.
//
//		DriverFactory.quitDriver();
//
//	}

}
