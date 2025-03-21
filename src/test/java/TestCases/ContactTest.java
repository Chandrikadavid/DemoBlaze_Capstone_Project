package TestCases;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.ContactPage;
import utilities.ExtentReportManager;

public class ContactTest extends BaseClass {
	ContactPage contactPage;

	@Parameters("browser") // Receive browser parameter from testng.xml
	@BeforeMethod
	public void setUp(String browser) throws IOException {
		invokeBrowser(browser); // Pass the parameter to BaseClass method
		contactPage = new ContactPage(driver);
		ExtentReportManager.startTest("Contact Form Test"); // Start Extent Report Test
	}

	@Test
	@Parameters({ "contactEmail", "contactName", "contactMessage" })
	public void testSubmitContactForm(String email, String name, String message)
			throws InterruptedException, IOException {
		ExtentReportManager.logInfo("Submitting contact form with Email: " + email + ", Name: " + name);
		contactPage.contactForm(email, name, message);
		screenshot("Contact Form");
		contactPage.submitContactForm();

		// Switch to the alert box and get text
		Alert alert = driver.switchTo().alert();
		String alertMessage = alert.getText();
		System.out.println("Alert Message: " + alertMessage);
		alert.accept();

		// Log success in Extent Reports
		ExtentReportManager.logPass("Contact form submitted successfully. Alert: " + alertMessage);

	}

	@AfterMethod
	public void tearDownTest() throws InterruptedException {
		closeBrowser(); // Close the browser
		ExtentReportManager.endTest(); // End Extent Report Test
	}
}
