package com.eventHub.stepdefinitions;

import com.eventHub.drivermanager.PageObjectManager;
import com.eventHub.pages.AddNewEventPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddNewEvent  {


	private final PageObjectManager pageObjectManager = new PageObjectManager();
	private AddNewEventPage addNewEvent;
	
	
	
@When("user clicks on Add New Event button")
public void user_clicks_on_add_new_event_button() {
	
	addNewEvent = pageObjectManager.getAddNewEvent();
	
	addNewEvent.creatingNewEvent();
	
}


	@Then("user lands on Add New Event page")
	public void user_lands_on_add_new_event_page() {
	 
	//addNewEvent.newEventTitleVisible();
		
	}
	

@Given("the user is on Event Page")
public void the_user_is_on_event_page() {
	
	addNewEvent = pageObjectManager.getAddNewEvent();
}


	@Given("when user add {string}, {string},{string}, {string}, {string}, {string},{string},{string},and {string}")
	public void when_user_add_and(String title, String discription, String category, String city, String venue, String eventDateTime, String price, String totalSeats, String image) {
	   
		
		
		
	addNewEvent.eventTitle(title);
 	addNewEvent.eventDescription(discription);
//		addNewEvent.eventCatogory(category);
//		addNewEvent.eventCity(city);
//		addNewEvent.eventAddress(venue);
//		
//		addNewEvent.eventDateTime(eventDateTime);
//		addNewEvent.eventPrice(price);
//		addNewEvent.eventSeat(totalSeats);
		
		
		
	}

	@When("the user click on {string} button")
	public void the_user_click_on_button(String EventButton) {
	    
		addNewEvent.addNewEventButton();
		
	}

	@Then("user is able to see New Event added Message with Booking Ref")
	public void user_is_able_to_see_new_event_added_message_with_booking_ref() {
	   
		
		
	}

	@Then("Click on view my booking to see the booking details")
	public void click_on_view_my_booking_to_see_the_booking_details() {
	   
	}

	@Then("Cancel the booking to complete the sceanrio")
	public void cancel_the_booking_to_complete_the_sceanrio() {
	    
		
		
	}


	

	
	
}
