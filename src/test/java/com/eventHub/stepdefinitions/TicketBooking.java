package com.eventHub.stepdefinitions;

import org.testng.Assert;

import com.eventHub.drivermanager.PageObjectManager;
import com.eventHub.pages.BookTicketPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TicketBooking {

	private final PageObjectManager pageObjectManager = new PageObjectManager();
    private BookTicketPage bookTicketPage;
	

    
@Given("User selects {int} ticket to purchase")
public void user_selects_ticket_to_purchase(Integer count) {

	bookTicketPage = pageObjectManager.getBookTicketPage();
	
	
    for (int i = 0; i < count; i++) {
        bookTicketPage.increaseTicketCount();
	
    }	
}

@When("User enters customer details {string}, {string}, and {string}")
public void user_enters_customer_details_and(String name, String email, String phone) {

	bookTicketPage.fillCustomerDetails(name, email, phone);
}

@When("User clicks on the confirm booking button")
public void user_clicks_on_the_confirm_booking_button() {
	bookTicketPage.clickConfirmBooking();
}

@Then("User should see the {string} message with a valid reference ID")
public void user_should_see_the_message_with_a_valid_reference_id(String expectedMsg) {
	
	Assert.assertTrue(bookTicketPage.getSuccessMessageText().contains(expectedMsg), "Success message mismatch!");
    String refId = bookTicketPage.getBookingReferenceID();
    Assert.assertNotNull(refId, "Booking reference ID was null!");
	
	
	
}

@When("User cancels the booked ticket")
public void user_cancels_the_booked_ticket() {
	bookTicketPage.clickViewMyBookings();
}

@Then("The booking status should update to {string}")
public void the_booking_status_should_update_to(String string) {
	bookTicketPage.clickCancelBooking();
    bookTicketPage.confirmCancelInModal();
}
	
	
	
	
	
}
