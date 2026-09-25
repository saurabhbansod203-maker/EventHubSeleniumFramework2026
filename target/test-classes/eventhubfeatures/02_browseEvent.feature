@regression
Feature: User Browse Events

Background:
    Given User is logged into the application

  Scenario: Verify user can browse available events
  When User navigates to the "Browse Events" section 
  And Multiple event cards should be displayed on the page
  And Click on Book Now button whichever event want to select
  Then Page redirect to TicketBooking Page
   