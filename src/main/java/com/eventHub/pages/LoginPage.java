package com.eventHub.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * LoginPage encapsulates UI elements and behavior for the Login screen.
 * Utilizes By locators and inherited BasePage wrappers to ensure thread safety
 * and handle dynamic waits.
 */

public class LoginPage extends BasePage {

	// ==========================================
	// 1. LOCATORS (Encapsulated as private By objects)
	// ==========================================
	// Clean, high-performance locators (using By.id instead of verbose CSS)

	private final By emailInput = By.cssSelector("input[id='email']");
	private final By passwordinput = By.cssSelector("input[id='password']");
	private final By loginbtn = By.cssSelector("button[id='login-btn']");

	// ==========================================
	// 2. CONSTRUCTOR
	// ==========================================
	/**
	 * Binds WebDriver session to super (BasePage) constructor. No local 'WebDriver
	 * driver' declaration needed—driver is inherited from BasePage.
	 */

	public LoginPage(WebDriver driver) {

		super(driver);  //Calling Base Class(Parent Class) constructor 

	}

	// ==========================================
	// 3. ATOMIC ACTIONS (Parameterized & Clean)
	// ==========================================

	/**
	 * Enters email address into the email input field.
	 * 
	 * @param email Dynamic value passed from Cucumber step definitions
	 */

	public void enterEmail(String email) {

		sendKeys(emailInput, email);
	}

	public void enterPassword(String password) {

		sendKeys(passwordinput, password);
	}

	/**
	 * Clicks the login submit button.
	 */

	public void clickLoginButton() {

		click(loginbtn);

	}

	// ==========================================
	// 4. BUSINESS WORKFLOWS & HELPER METHODS
	// ==========================================

	/**
	 * High-level business action: Performs complete login sequence in a single
	 * call. Reduces duplicate code in Step Definitions.
	 */

	public void loginToApplication(String email, String password) {

		enterEmail(email);
		enterPassword(password);
		clickLoginButton();

	}

	/**
	 * Returns the current page title.
	 */

	public String getLoginPageTitle() {

		return driver.getTitle();

	}

	/**
	 * Checks if the login button is visible on the UI.
	 * 
	 * @return
	 */

	public boolean isLoginButtonDisplayed() {

		return isDisplayed(loginbtn);

	}

}
