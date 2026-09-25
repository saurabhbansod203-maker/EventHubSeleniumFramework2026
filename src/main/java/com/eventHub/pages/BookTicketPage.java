package com.eventHub.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * BookTicketPage encapsulates UI locators and user interactions for the ticket booking 
 * and booking management workflows.
 * Extends BasePage to leverage robust explicit waits and thread-safe interactions.
 */
public class BookTicketPage extends BasePage {

    // ==========================================
    // 1. LOCATORS (Encapsulated private By objects)
    // ==========================================
    // Clean, stale-element-proof locators using By strategies
	private final By TicketPage = By.xpath("//h1[text()='Hollywood Monsoon Night — Los Angeles']");
	
    private final By incrementTicketBtn = By.xpath("//button[text()='+']");
    private final By customerNameInput = By.id("customerName");
    private final By customerEmailInput = By.id("customer-email");
    private final By phoneNumberInput = By.id("phone");
    private final By confirmBookingBtn = By.id("confirm-booking");
    
    // Confirmation & Message Locators
    private final By successMessage = By.xpath("//h3[contains(text(), 'Booking Confi')]");
    private final By bookingRefText = By.xpath("//span[starts-with(text(), 'H-')]");
    
    // Management Locators
    private final By viewMyBookingBtn = By.xpath("//button[text()='View My Bookings']");
    private final By cancelBookingBtn = By.xpath("//button[text()='Cancel Booking']");
    private final By confirmCancelYesBtn = By.cssSelector("button#confirm-dialog-yes");

    
    // ==========================================
    // 2. CONSTRUCTOR
    // ==========================================
    /**
     * Reuses driver and wait initialization from BasePage.
     * No local 'WebDriver driver' instance variable needed.
     */
    public BookTicketPage(WebDriver driver) {
        super(driver);
    }

    // ==========================================
    // 3. ATOMIC & WORKFLOW ACTIONS
    // ==========================================

    
public WebElement TicketTextVisible() {
		
		return waitforVisibility(TicketPage);

	}
    
    
    /**
     * Clicks the '+' button to increase the ticket count.
     */
    public void increaseTicketCount() {
        click(incrementTicketBtn);
    }

    /**
     * Fills customer details into the booking form.
     * 
     * @param name  Customer full name
     * @param email Customer email address
     * @param phone Customer phone number
     */
    public void fillCustomerDetails(String name, String email, String phone) {
        sendKeys(customerNameInput, name);
        sendKeys(customerEmailInput, email);
        sendKeys(phoneNumberInput, phone);
    }

    /**
     * Clicks the Confirm Booking button.
     */
    public void clickConfirmBooking() {
        click(confirmBookingBtn);
    }

    /**
     * High-Level Composite Workflow: Completes the entire booking flow in one step.
     */
    public void bookTickets(int numberOfTickets, String name, String email, String phone) {
        for (int i = 0; i < numberOfTickets; i++) {
            increaseTicketCount();
        }
        fillCustomerDetails(name, email, phone);
        clickConfirmBooking();
    }

    // ==========================================
    // 4. VERIFICATIONS & RETRIEVALS
    // ==========================================

    /**
     * Fetches text from the booking success banner after ensuring visibility.
     * @return Confirmation message string
     */
    public String getSuccessMessageText() {
        return getText(successMessage);
    }

    /**
     * Retrieves the unique Booking Reference ID (e.g., "H-12345").
     * @return Booking Reference ID string
     */
    public String getBookingReferenceID() {
        return getText(bookingRefText);
    }

    /**
     * Checks if the success message is displayed on the UI.
     */
    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }

    // ==========================================
    // 5. CANCELLATION & DIALOG MANAGEMENT
    // ==========================================

    /**
     * Clicks the View My Bookings button.
     */
    public void clickViewMyBookings() {
        click(viewMyBookingBtn);
    }

    /**
     * Clicks the Cancel Booking button.
     */
    public void clickCancelBooking() {
        click(cancelBookingBtn);
    }

    /**
     * Handles modal dialog confirmation for booking cancellation.
     */
    public void confirmCancelInModal() {
        click(confirmCancelYesBtn);
    }
}