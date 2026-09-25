@regression
Feature: Ticket Booking and Management

Background:
    Given User is logged into the application
    When User navigates to the "Browse Events" section
   And Click on Book Now button whichever event want to select
  Then Page redirect to TicketBooking Page

  Scenario Outline: Book and cancel event tickets
    Given User selects <TicketCount> ticket to purchase
    When User enters customer details "<Name>", "<EmailID>", and "<PhoneNumber>"
    And User clicks on the confirm booking button
    Then User should see the "Booking Confirmed!" message with a valid reference ID
    When User cancels the booked ticket
    Then The booking status should update to "Cancelled"

    Examples:
      | TicketCount | Name    | EmailID         | PhoneNumber |
      | 2          | Samsung | sam23@gmail.com | 9878767654  |