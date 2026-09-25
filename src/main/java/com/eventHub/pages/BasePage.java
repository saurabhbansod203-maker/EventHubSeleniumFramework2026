//# Common UI actions (waits, clicks, JS)

package com.eventHub.pages;

import java.time.Duration;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.eventHub.utils.LogUtils;

//This page is created to implements the waits in pages for ex. used in login page
// So that when page login page open it wait for given second then executes

/**
 * BasePage serves as the parent class for all Page Objects in the framework.
 * It encapsulates WebDriver actions with explicit dynamic waits to prevent 
 * flaky tests, StaleElementReferenceException, and hardcoded Thread.sleep calls.
 */


public class BasePage {  // BasePage Parent class 
	
	protected WebDriver driver;  // The protected modifier makes a variable or method accessible only within its own package and by child classes (subclasses) in other packages.
	protected WebDriverWait wait ;
	
	private static final int DEFAULT_TIMEOUT = 10;
	
	
	 // Constructor created - Will run assign value to driver and wait will run because of Constructor created
	
	/**
     * Constructor initializes driver and wait instances for child classes.
     * 
     * @param driver Active ThreadLocal WebDriver instance passed from PageObjectManager
     */

	
	
	public  BasePage(WebDriver driver) 
	{
	
		// WHY THIS ASSIGNMENT:
	        // Essential to prevent NullPointerExceptions when child pages call driver methods.
   	this.driver = driver;
		
	 this.wait = new WebDriverWait (driver, Duration.ofSeconds(DEFAULT_TIMEOUT));  // wait for 10 sec to load 
	
	}
	
	// ==========================================
    // EXPLICIT WAIT WRAPPERS (BY LOCATOR & WEBELEMENT)
    // ==========================================

    /**
     * Waits until an element located by By locator is visible in the DOM.
     * Accepting By locator prevents StaleElementReferenceException.
     */
	
	public WebElement waitforVisibility(By locator)
	{
		
		
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		

		
	}
	
	/**
     * Overloaded method for waiting on an existing WebElement instance.
     */
	
	
	public WebElement waitforVisibility(WebElement element)
	{
		
		
		return wait.until(ExpectedConditions.visibilityOf(element));
		

		
	}
	
	
	
	/**
     * Waits until an element is visible and enabled for clicking.
     */
	
	
	public WebElement waitForClickability(By locator)
	{
		
		
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	

		
	}
	
	
	// ==========================================
    // ENCAPSULATED UI ACTION WRAPPERS
    // ==========================================

    /**
     * Combined Action: Waits for element clickability, then clicks.
     */
	
	
	public void click(By locator) {
		
		// WHY ENCAPSULATE:
        // Individual Page Objects don't need to write wait code for every click.
		
		waitForClickability(locator).click();
		
		
		
	}
	
	/**
     * Combined Action: Waits for visibility, clears existing text, and types input.
     */
	
	public void sendKeys(By locator, String text)
	{
		WebElement element = waitforVisibility(locator);
		element.clear();
		element.sendKeys(text);
		
	}
	
	/**
     * Fetches inner text of an element after ensuring visibility.
	 * @return 
     */
	
	public String getText(By locator)
	{
		
		 return waitforVisibility(locator).getText();
		
		
	}
	
	/**
    * Safely checks if an element is displayed on the UI without throwing unhandled exceptions.
    */
	
	public boolean isDisplayed(By locator)
	{
		try {
			
			  return waitforVisibility(locator).isDisplayed();
			
		}catch(Exception e) {
			
			return false;
				
		}	
		
	}
	
	// ==========================================
    // ADVANCED / FALLBACK ACTION WRAPPERS
    // ==========================================

    /**
     * JavaScript Click fallback for elements intercepted by overlays or animations.
     */
	
	public void jsClick(By locator) {
		
		WebElement element = waitforVisibility(locator);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].click();", element);
		
		
	}
	
	/**
     * Scrolls the window until the target element is in view.
     */
	
	
	public void scrollToElement(By locator) 
	
	{
        WebElement element = waitforVisibility(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
	
	}
	
	//arguments[0] is a placeholder in JavaScript that represents the first variable (element) you pass from your Selenium Java code into the script.
	
	
	/**
     * Scrolls the element directly into the CENTER of the viewport 
     * to avoid sticky top headers and bottom footers.
     */
    public void scrollToElementCenter(WebElement element) {
    	
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
    }
	
	
    /**
     * Attempts standard click after scrolling to center.
     * If intercepted, falls back seamlessly to JavaScript Click.
     */
    public void safeClick(By locator) throws ElementClickInterceptedException {
    	
       WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        
        // Step 1: Scroll to middle of screen
        scrollToElementCenter(element);

        try {
            // Step 2: Wait for element to be clickable and perform native click
        	
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
            
        } catch (ElementNotInteractableException e) {
            LogUtils.warn("Native click intercepted on " + locator + ". Executing JavaScript click fallback.");
            
            // Step 3: Fallback JavaScript Click (Bypasses UI overlap checks)
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        }
    }

    
	
}
