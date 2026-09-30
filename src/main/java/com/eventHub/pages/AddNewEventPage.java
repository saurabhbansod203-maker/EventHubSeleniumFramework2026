package com.eventHub.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.eventHub.utils.ElementUtils;

public class AddNewEventPage extends BasePage {
	

	private final By creatingNewEvent = By.xpath("//div[@class='mt-12 flex justify-center']/a");

	private final By newEventTitle = By.xpath("//h2[text()='+ New Event']");

	private final By addTitle = By.cssSelector("#event-title-input");

	private final By addDescription = By.xpath("//label[text()='Description']/following-sibling::textarea");

	private final By dropdown = By.id("category");
	
	private final By city = By.id("city");

	private final By address = By.id("venue");

	private final By eventDateTime = By.xpath("//label[@for='event-date-&-time']/following-sibling::input");

	//private final WebElement eventDateTime = driver.findElement(By.xpath("//label[@for='event-date-&-time']/following-sibling::input"));
	
	private final By eventPrice = By.xpath("//div[@class='flex flex-col gap-1 ']//input[@id='price-($)']");

	private final By eventSeats = By.cssSelector("label+ input#total-seats");

//	private final By imageUpload = By.xpath("//label[text()='Image URL (optional)']/following-sibling::input");

	private final By addEventButton = By.xpath("//button[text()='+ Add Event']");
	
	private final By deleteEventButton = By.xpath("(//button[@id='delete-event-btn'])[1]");

	private final By eventDeleteAlertbutton = By.id("confirm-dialog-yes");
	
	public AddNewEventPage(WebDriver driver) {
		super(driver);
		this.driver = driver;

	}

	public void creatingNewEvent() {

		safeClick(creatingNewEvent);

	}

	public void newEventTitleVisible() {

		isDisplayed(newEventTitle); // return true if text return

	}

	public void eventTitle(String title) {

		sendKeys(addTitle, title);

	}

	public void eventDescription(String description) {

		sendKeys(addDescription, description);

	}

	public void eventCatogory(String category) {

		 // 1. Wait for visibility using the By locator instead of the element
		
		waitforVisibility(dropdown);
		
		// 2. Safely find the element now that the wait has completed
		
         WebElement categoryElement = driver.findElement((dropdown));
		
         // 3. Interact with the dropdown
         
		 ElementUtils.selectByVisibleText(categoryElement, category);
		
	
	}

	public void eventCity(String ecity) {

		sendKeys(city, ecity);

	}

	public void eventAddress(String eaddress) {

		sendKeys(address, eaddress);

	}

	public void eventDateTime(String dateTime) {
		
	waitforVisibility(eventDateTime);
			
    WebElement dateT = driver.findElement((eventDateTime));
   
	 ElementUtils.setDateTimeValue(driver,dateT,dateTime);
		
	 dateT.sendKeys("30102026");
	 dateT.sendKeys(Keys.TAB);
	 dateT.sendKeys("11:30PM");
	
	 //sendKeys(eventDateTime, dateTime);
	

	}

	public void eventPrice(String price) {

		sendKeys(eventPrice, price);

	}

	public void eventSeat(String seat) {

		sendKeys(eventSeats, seat);

	}

	public void addNewEventButton() {

		safeClick(addEventButton);
		

	}
	
	public void deleteEvent() {

		safeClick(deleteEventButton);
		waitforVisibility(eventDeleteAlertbutton);
		click(eventDeleteAlertbutton);

		

	}
	
	


}
