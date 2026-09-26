@smoke
Feature: EventHub Add New Event Functionality

Background:

    Given User is logged into the application
    When User navigates to the "Browse Events" section

Scenario Outline: Verify that user can able to add all given details for new Event

Given user clicks on Add New Event button
And the user is on Event Page
And when user add "<Title>", "<Description>","<Category>", "<City>", "<Venue>", "<EventDate&Time>","<Price>","<Total Seats>",and "<Image>"
When the user click on "Add Event" button
Then user is able to see New Event added Message with Booking Ref
And Click on view my booking to see the booking details
But Cancel the booking to complete the sceanrio

Examples:

|Title      |            Description                                            | Category|City|Venue|EventDate&Time|Price|Total Seats|Image|

|New Year Party|  Welcome to new Year Party 2027!!! |Festival | Pune| Hyatt Hotel | 23/Sept/2026|300| 500|  Image.jpg         |





