package com.eventHub.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddNewEventPage extends BasePage {

	
	private final By creatingNewEvent = By.xpath("//div[@class='mt-12 flex justify-center']/a");
	
	private final By newEventTitle = By.xpath("//h2[text()='+ New Event']");
	
	private final By addTitle = By.cssSelector("#event-title-input");

	private final By addDescription = By.xpath("//label[text()='Description']/following-sibling::textarea");

	private final By categoryDropdown = By.id("category");

	private final By city = By.id("city");
	
	private final By address = By.id("venue");
	
	private final By eventDateTime = By.xpath("//label[@for='event-date-&-time']/following-sibling::input");
	
	private final By eventPrice = By.xpath("//div[@class='flex flex-col gap-1 ']//input[@id='price-($)']");
	
	private final By eventSeats  = By.cssSelector("label+ input#total-seats");
	
	private final By imageUpload  = By.xpath("//label[text()='Image URL (optional)']/following-sibling::input");
	
	private final By addEventButton  = By.xpath("//button[text()='+ Add Event']");
	
	
	
	public AddNewEventPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		
		
	}
	
	
	
	

	public void creatingNewEvent() {
		

		safeClick(creatingNewEvent);
		
		
		
	}
	
	
	public void newEventTitleVisible() {
		
		
	  isDisplayed(newEventTitle);       // return true if text return 

		
	}
	

	

	public void eventTitle(String title) {
		
		
		/// Explicitly wait for the form input field to load before trying to type into it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(addTitle));
        
        sendKeys(addTitle, title);
		
		
	}
	
	public void eventDescription(String description) {
		
		
		sendKeys(addDescription, description);
		

		
	}
	
	public void eventCatogory(String category) {
		
		sendKeys(categoryDropdown, category);
		

		
	}
	
	public void eventCity(String ecity) {
		
		sendKeys(city, ecity);
		

		
	}
	
	public void eventAddress(String eaddress) {
		
		sendKeys(address, eaddress);
		

		
	}
	
	public void eventDateTime(String dateTime) {
		
		sendKeys(eventDateTime, dateTime);
		

		
	}
	
	public void eventPrice(String price) {
		
		sendKeys(eventPrice, price);
		

		
	}

	public void eventSeat(String seat) {
		
		sendKeys(eventSeats, seat);
		

		
	}
	
	public void addNewEventButton() {
		
		
		click(addEventButton);

		
	}
	

	


}
