package com.eventHub.drivermanager;


//# Encapsulated ThreadLocal getter/setter
import org.openqa.selenium.WebDriver;

import com.eventHub.pages.AddNewEventPage;
import com.eventHub.pages.BookTicketPage;
import com.eventHub.pages.BrowserEventPage;
import com.eventHub.pages.LoginPage;

//This class is created to manage Page Object 
//Object creation under pages .. LoginPage, BrowserEvent

/**
 * PageObjectManager follows the Factory Design Pattern.
 * It is responsible for lazily creating and holding single instances of Page Objects 
 * for the duration of a single scenario thread execution.
 */

public class PageObjectManager {

	 private final WebDriver driver;
	
	// Page Object references (initialized as null by default)
	private LoginPage loginPage;
	private BrowserEventPage browserEventPage;
	private BookTicketPage bookTicketPage;
	private AddNewEventPage addNewEventPage;
	
	
	// WHY CONSTRUCTOR INJECTION:
    // We bind the driver once when PageObjectManager is instantiated.
    // In Cucumber, Dependency Injection (like PicoContainer) manages this per-scenario.
	
	public PageObjectManager(WebDriver driver)
	{
		  this.driver = driver;
		
		
	}
	
	// ALTERNATIVE CONSTRUCTOR: 
    // Pulls thread-safe driver directly from DriverFactory if no driver is passed.
	
	
	public PageObjectManager() {
		
        this.driver = DriverFactory.getDriver();
    }
	
	
	/**
     * Lazily instantiates and returns the LoginPage object.
     * Uses ternary evaluation for clean, concise initialization.
     */
	
	public LoginPage getLoginPage()
	{
		// WHY LAZY INITIALIZATION:
        // Creates the object ONLY when a step definition needs it. 
        // Prevents memory waste for pages not used in a specific feature file.
		//This line of code is a concise way of implementing what is known as Lazy Initialization (specifically, a Thread-Safe-like or Simple Singleton-style check for a Page Object)
	//  ternary operator (? :) instead of if - else
		
   //if (loginPage == null) {
//	    loginPage = new LoginPage(driver); // 1. Create it if it doesn't exist
//	    return loginPage;
//	} else {
//	    return loginPage;                  // 2. Just return it if it already exists
//	}
	//The ? Part (If True): 
		return (loginPage == null ) ? loginPage = new LoginPage(driver) : loginPage;
		
	}
	
	
	/**
     * Lazily instantiates and returns the BrowserEventPage object.
     */
	public BrowserEventPage getBrowserEventPage( )
	
	{
		  
		return (browserEventPage == null ) ? browserEventPage = new BrowserEventPage(driver) : browserEventPage;
		
		
	}
	
	
public BookTicketPage getBookTicketPage( )
	
	{
		  
	   return (bookTicketPage==null) ? bookTicketPage = new BookTicketPage(driver) : bookTicketPage;
		
		
	}


	
public AddNewEventPage getAddNewEvent( )

{
	  
   return (addNewEventPage==null) ? addNewEventPage = new AddNewEventPage(driver) : addNewEventPage;
	
	
}

	
	
	
	
}
