package com.eventHub.stepdefinitions;

import org.testng.Assert;

import com.eventHub.drivermanager.PageObjectManager;
import com.eventHub.pages.LoginPage;
import com.eventHub.utils.ConfigReader;
import com.eventHub.utils.LogUtils;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

// Note:- all method in cucumber in no-static , the cucumber create an object of this below method internally

/**
 * LoginPageSteps bridges Gherkin feature scenarios with UI page interactions.
 * It strictly delegates DOM actions to LoginPage and contains assertions for validations.
 */

public class LoginPageSteps {

	// PageObjectManager retrieves the ThreadLocal driver automatically
	
	private final PageObjectManager pageObjectManager = new PageObjectManager();
	private LoginPage loginpage;
	
	// =========================================================================
    // 1. COMPOSITE PRE-CONDITION STEP
    // Used in Background for 02_browseEvent.feature, 03_ticketBooking.feature, etc.
    // =========================================================================

    @Given("User is logged into the application")
    public void user_is_logged_into_the_application() {
    	loginpage = pageObjectManager.getLoginPage();

        // 1. Fetch credentials dynamically from config.properties
        String defaultEmail = ConfigReader.getProperty("username");
        String defaultPassword = ConfigReader.getProperty("password");

        LogUtils.info("Executing composite login for pre-condition using: " + defaultEmail);

        // 2. Perform complete UI login sequence in one call
        loginpage.loginToApplication(defaultEmail, defaultPassword);
    }

 // =========================================================================
    // 2. ATOMIC STEPS
    // Used specifically in 01_login.feature to test the login feature step-by-step
    // =========================================================================

@Given("The user is on the landing page")
public void the_user_is_on_the_landing_page() {
    
	loginpage = pageObjectManager.getLoginPage();
    
    // Validate landing page is loaded (Browser was already opened by ApplicationHooks)
    Assert.assertTrue(loginpage.isLoginButtonDisplayed(), 
            "Landing page not displayed or Login button missing!");
	
	
	
}

@When("User enters email {string} and password {string}")
public void user_enters_email_and_password(String email, String password) {
    
	loginpage.enterEmail(email);
	loginpage.enterPassword(password);
	
}



	
	@When("User clicks on the login button")
	public void user_clicks_on_the_login_button() {
		
		loginpage.isLoginButtonDisplayed();
		loginpage.clickLoginButton();
		
		
	}
	@Then("The user should see the {string} logo displayed")
	public void the_user_should_see_the_logo_displayed(String expectedTitle) {
	
		
		String actualTitle= loginpage.getLoginPageTitle();
		 Assert.assertTrue(actualTitle.contains(expectedTitle),"Title mismatch! Expected: " + expectedTitle + " | Actual: " + actualTitle);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
