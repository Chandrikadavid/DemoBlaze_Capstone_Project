package pages;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

public class LoginPage {
	WebDriver driver;
	FluentWait<WebDriver> fluentWait;

	// Locating Web Elements using @FindBy annotation
	@FindBy(id = "login2")
	WebElement loginButton;

	@FindBy(id = "loginusername")
	WebElement usernameField;

	@FindBy(id = "loginpassword")
	WebElement passwordField;

	@FindBy(xpath = "//button[text()='Log in']")
	WebElement submitButton;

	@FindBy(xpath = "//a[contains(text(),'Welcome')]") // XPath to verify successful login
	WebElement welcomeMessage;

	// Constructor initializes elements using PageFactory and sets up Fluent Wait
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

		// Initializing Fluent Wait
		fluentWait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10)) // Maximum wait time of 10 seconds
				.pollingEvery(Duration.ofMillis(500)) // Checks for the element every 500 milliseconds
				.ignoring(NoSuchElementException.class); // Ignores NoSuchElementException
	}


	public void login(String username, String password) throws InterruptedException, IOException {
		// Fluent wait for the login button to be clickable and then click it
		fluentWait.until(driver -> loginButton).click();

		// Fluent wait for the username field to be visible, then send username
		fluentWait.until(driver -> usernameField).sendKeys(username);

		// Enter password directly as it's already located
		passwordField.sendKeys(password);

		// Click on the login submit button
		submitButton.click();
	}

	public boolean isUserLoggedIn() {
		try {
			// Fluent wait to check if welcome message appears after login
			return fluentWait.until(driver -> welcomeMessage.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}
}
