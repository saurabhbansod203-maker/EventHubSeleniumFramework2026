package com.eventHub.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UpcommingEventsPage extends BasePage {
	
	private final By BookNow = By.xpath("(//div[@class='p-4 flex flex-col flex-1']//child::div//a[@id='book-now-btn'])[2]");
	
	private final By upcommingEvent = By.xpath("//h1[text()='Upcoming Events']");
	
	public UpcommingEventsPage(WebDriver driver) 
	{
		 
		super(driver);       // super() to invoke the parent class's constructor.
	
		
	}
	
	

	
	public void upcommingEventVisible() {

		waitforVisibility(upcommingEvent);

	}
	
	public void BookNowEvent() {

		waitForClickability(BookNow);
		click(BookNow);

	}
	
	
	
	
	
	
}
