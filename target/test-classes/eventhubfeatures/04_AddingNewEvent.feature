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
Then Cancel the booking to complete the sceanrio

Examples:

|Title      |            Description                                            | Category|City      |Venue                                      | EventDate|Price|Total Seats|Image|

|New Year Party|  Welcome to new Year Party 2027!!! |Workshop| Pune | Hyatt Hotel Mundhwa Pune |2026-10-31|300| 500|  Image.jpg         |





