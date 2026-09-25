package com.eventHub.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * ScreenshotUtils captures screenshots in byte array format for reports 
 * or saves files to local disk.
 */

public class ScreenshotUtils {

	
	/**
     * Captures screenshot as byte array for inline Cucumber/Extent report attachment.
	 * @return 
     */
	
	
	public static byte[] captureScreenshotAsBytes(WebDriver driver) {
		
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		
		
	}
	
	/**
     * Captures screenshot as Base64 string.
	 * @return 
     */
	
	public static String  captureScreenshotAsBase64(WebDriver driver) {
		
		return ((TakesScreenshot)driver ).getScreenshotAs(OutputType.BASE64);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
