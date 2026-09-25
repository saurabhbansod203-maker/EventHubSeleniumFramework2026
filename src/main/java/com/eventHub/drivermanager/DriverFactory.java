//# ThreadLocal WebDriver management
package com.eventHub.drivermanager;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

//This class is created to to create new driver instances 
//or return the driver instance if already available from getDriver() method
//for ex. like chromeDriver, or firefox driver 

/**
 * DriverFactory is responsible for initializing and managing the lifecycle of WebDriver instances.
 * It uses ThreadLocal to guarantee thread-safety during parallel test execution.
 */

public class DriverFactory {
	
	// WHY THREADLOCAL:
    // Parallel execution creates multiple threads executing tests at the same time.
    // ThreadLocal allocates an isolated WebDriver object to each thread. 
    // Without it, Thread-2 would overwrite Thread-1's browser, causing random test crashes.
	
	public static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();  // Final  - a non-access modifier used to restrict modification and enforce stability in your code
	 																																	 //Static - static keyword is a non-access modifier used mainly for memory management. It signifies that a member (variable, method, block, or nested class) belongs to the class itself rather than to any specific instance (object) of that class.
	/**
     * Initializes the WebDriver session based on the requested browser.
     * 
     * @param browser Browser name passed from config.properties or CLI (-Dbrowser=chrome)
     * @return Thread-safe WebDriver instance
     */
	

	public  static WebDriver initDriver(String browser) // Opening the browser & returning the object of driver
	{
	// WHY STRING SANITIZATION:
    // Null checks and lowercase normalization prevent NullPointerExceptions 
    // and handle case mismatches (e.g., "CHROME", " Chrome ") gracefully.
	
	String browserName = (browser == null) ? "chrome" : browser.toLowerCase().trim();
	
	System.out.println("LOG [INFO]: Initializing " + browserName + " on Thread ID: " + Thread.currentThread().getId());
	
	// WHY SWITCH CASE:
    // Cleaner, faster execution lookup, and avoids nested conditional bloat.
	
	switch(browserName) {
	
	case "chrome":
		
		//Creates an object to set rules and settings for the Chrome browser before it starts.
	  ChromeOptions chromeOption = new ChromeOptions();
	
	  chromeOption.addArguments("--remote-allow-origins=*");// Allows connections from any website origin to the browser's developer tools.This fixes connection errors when your automation tool tries to talk to newer versions of Chrome. 
	 // chromeOption.addArguments("--headless=new");
	  tlDriver.set(new ChromeDriver(chromeOption));
	  break;
	  
	case "firefox":
		tlDriver.set(new FirefoxDriver());
		break;
		
	case "edge":
		tlDriver.set(new EdgeDriver());
		break;
		
	default: 
		// WHY THROW EXCEPTION:
        // "Fail-Fast" principle. If a tester typos "chrmoee" in config, 
        // throw a clear error immediately instead of failing silently with nulls later.
		
		throw new IllegalArgumentException( "Unsupported browser: '" + browser + "'. Please pass chrome, firefox, or edge.");
		
		
	}
	// Apply global driver behaviors
	
	getDriver().manage().deleteAllCookies();
    getDriver().manage().window().maximize();
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
       return getDriver();
	
	
	}
	
	/**
     * Returns the WebDriver instance allocated strictly to the calling thread.
     */
	
	//synchronized is a keyword used to control access to shared resources by multiple threads.
	//It ensures that only one thread can execute a specific block or method at a time, preventing race conditions and data inconsistency
	public static synchronized WebDriver getDriver()
	
	{
		  
		return tlDriver.get();
		
		
		
	}
	/**
     * Cleanly shuts down the browser and clears the ThreadLocal memory reference.
     */
	
	public static void quitDriver()
	{
		 
		if(getDriver() != null)
		{
			// WHY QUIT: Closes all open browser tabs and terminates the browser session process.
            getDriver().quit();
			
		}
		
		// WHY REMOVE:
        // CRITICAL MEMORY MANAGEMENT! ThreadLocal variables stay in memory if not explicitly removed.
        // In long-running thread pools (like TestNG parallel runners), failing to call .remove() causes severe memory leaks.
		
		tlDriver.remove();
		
		
	}
	
	
	
	


}

