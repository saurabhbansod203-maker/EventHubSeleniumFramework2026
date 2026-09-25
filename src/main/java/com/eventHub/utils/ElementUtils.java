package com.eventHub.utils;

import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

/**
 * ElementUtils provides generic helper methods for complex Selenium
 * interactions such as frames, window handles, dropdowns, and mouse movements.
 */

public class ElementUtils {

	// ==========================================
	// DROPDOWN UTILITIES
	// ==========================================

	public static void selectByVisibleText(WebElement element, String text)

	{
		Select select = new Select(element);
		select.selectByVisibleText(text);

	}

	public static void selectByValue(WebElement element, String value)

	{
		Select select = new Select(element);
		select.selectByVisibleText(value);

	}

	// ==========================================
	// WINDOW & TAB HANDLERS
	// ==========================================
	/**
	 * Switches to a window/tab by matching its page title.
	 */

	public static void switchToWindowByTitle(WebDriver driver, String targetTitle) {
		Set<String> windowHandles = driver.getWindowHandles();

		for (String handles : windowHandles) {

			driver.switchTo().window(handles);
			
			if(driver.getTitle().contains(targetTitle))
			{
				
				break;
				
			}

		}

	}
	
	// ==========================================
    // FRAME & ALERT UTILITIES
    // ==========================================
	
	public static void switchToFrameByNameOrId(WebDriver driver , String nameOrId)
	{
		driver.switchTo().frame(nameOrId);
		
		
	}
	
	public static void defaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }
	
	public static String acceptAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }
	
	// ==========================================
    // MOUSE & ACTIONS UTILITIES
    // ==========================================
	
	
	public static void hoverOverElement(WebDriver driver, WebElement element) {{}
	
	Actions actions = new Actions(driver);
	actions.moveToElement(element).perform();
	
	
	
	}
	
	
	/**
     * Highlights an element on the screen (useful during debugging or screenshots).
     */
	
//	public static void highlightElement(WebDriver driver, WebElement element) {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("arguments[0].style.border='3px solid red'", element);
//    }
//	
	
	
	

}
