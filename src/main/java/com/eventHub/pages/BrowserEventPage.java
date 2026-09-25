package com.eventHub.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class BrowserEventPage extends BasePage {

	private final By logoText = By.xpath("//span[text()='EventHub']");
	private final By Browse = By.xpath("//span[text()='Browse Events →']");
	private final By upcommingEvent = By.xpath("//h1[text()='Upcoming Events']");
	private final By BookNow = By.xpath("(//div[@class='p-4 flex flex-col flex-1']//child::div//a[@id='book-now-btn'])[2]");
	private final By TicketPage = By.xpath("//h1[text()='Hollywood Monsoon Night — Los Angeles']");
	

	
	private final By eventTab = By.id("#nav-events");

	// Why= this constructor will help to below element to load for 10 second to
	// load

	public BrowserEventPage(WebDriver driver) {
		super(driver);

	}

	public WebElement LogoTextVisible() {
		
		return waitforVisibility(logoText);

	}

	public void eventBrowserClick() {
		click(Browse);

	}

	public void upcommingEventVisible() {

		waitforVisibility(upcommingEvent);

	}
	
	public void EventTab() {

		click(eventTab);

	}
	
	public void BookNowEvent() {

		waitForClickability(BookNow);
		click(BookNow);

	}
	
public WebElement TicketTextVisible() {
		
		return waitforVisibility(TicketPage);

	}
	

}
