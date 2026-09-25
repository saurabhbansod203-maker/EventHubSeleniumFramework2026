package com.eventHub.stepdefinitions;

import com.eventHub.drivermanager.PageObjectManager;
import com.eventHub.pages.BrowserEventPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BrowserEventStep {

	private final PageObjectManager pageObjectManager = new PageObjectManager();
	private BrowserEventPage browseEvent;


	@When("User navigates to the {string} section")
	public void user_navigates_to_the_section(String string) {
	
		browseEvent = pageObjectManager.getBrowserEventPage();
		browseEvent.eventBrowserClick();
		
		
	}
	
	@When("Multiple event cards should be displayed on the page")
	public void multiple_event_cards_should_be_displayed_on_the_page() {
	  
	
		browseEvent.upcommingEventVisible();
	
		
	}
	
	@When("Click on Book Now button whichever event want to select")
	public void click_on_book_now_button_whichever_event_want_to_select() {
	  
		

		browseEvent.BookNowEvent();
		
	}

	@Then("Page redirect to TicketBooking Page")
	public void page_redirect_to_ticket_booking_page() {
	    
		browseEvent.TicketTextVisible();
		
	}


	
	

}
