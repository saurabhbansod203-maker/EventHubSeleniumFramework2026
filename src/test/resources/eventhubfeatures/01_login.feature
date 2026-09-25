    
 Feature: User Authentication

Background:
    Given The user is on the landing page

  @regression
  Scenario: Successful login with valid credentials
    When User enters email "samwilson33@gmail.com" and password "Samwilson@123"
    And User clicks on the login button
    Then The user should see the "EventHub" logo displayed