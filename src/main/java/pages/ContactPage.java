package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.ExtentReportManager;

public class ContactPage {
	WebDriver driver;

	// @FindBy Annotation to find the elements
	@FindBy(xpath = "//a[contains(text(),'Contact')]")
	WebElement contactBtn;

	@FindBy(id = "recipient-email")
	WebElement emailField;

	@FindBy(id = "recipient-name")
	WebElement nameField;

	@FindBy(id = "message-text")
	WebElement messageField;

	@FindBy(xpath = "//button[text()='Send message']")
	WebElement sendBtn;

	// Constructor with page factory
	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void contactForm(String email, String name, String message) throws InterruptedException, IOException {
		contactBtn.click();
		emailField.sendKeys(email);
		nameField.sendKeys(name);
		messageField.sendKeys(message);
		ExtentReportManager.logPass("Form filled successfully");
	}
	
	public void submitContactForm() throws InterruptedException {
		Thread.sleep(2000);
		sendBtn.click();
	}
}
