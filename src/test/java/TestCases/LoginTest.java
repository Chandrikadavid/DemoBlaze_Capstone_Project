package TestCases;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.ExtentReportManager;
import base.BaseClass;
import pages.LoginPage;

public class LoginTest extends BaseClass {
	LoginPage loginPage;

	@Parameters("browser") // Receive browser parameter from testng.xml
	@BeforeMethod
	public void setUp(String browser) throws IOException {
		invokeBrowser(browser);
		loginPage = new LoginPage(driver);
		ExtentReportManager.startTest("Login Test");
	}

	@Test(priority = 1)
	public void testValidLogin() throws InterruptedException, IOException {
		ExtentReportManager.logInfo("Starting Valid Login Test");

		loginPage.login(prop.getProperty("validUsername"), prop.getProperty("validPassword"));

		// Capture screenshot after login attempt
		screenshot("Valid Login");
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.alertIsPresent());

			Alert alert = driver.switchTo().alert();
			String alertMessage = alert.getText();
			System.out.println("Alert Message: " + alertMessage);

			alert.accept();
			ExtentReportManager.logPass("Expected alert "+alertMessage);
		} catch (TimeoutException e) {
			ExtentReportManager.logFail("Expected alert not found for invalid login.");
		}

		boolean isLoggedIn = loginPage.isUserLoggedIn();
		if (isLoggedIn) {
			ExtentReportManager.logPass("Login successful with valid credentials.");
		} else {
			ExtentReportManager.logFail("Login failed with valid credentials.");
		}

	}

	@Test(priority = 2)
	public void testInvalidLogin() throws InterruptedException, IOException {
		ExtentReportManager.logInfo("Starting Invalid Login Test");
		loginPage.login(prop.getProperty("invalidUsername"), prop.getProperty("invalidPassword"));

		screenshot("Invalid Login");

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.alertIsPresent());

			Alert alert = driver.switchTo().alert();
			String alertMessage = alert.getText();
			System.out.println("Alert Message: " + alertMessage);

			alert.accept();
			ExtentReportManager.logPass("Expected alert "+alertMessage);
		} catch (TimeoutException e) {
			ExtentReportManager.logFail("Expected alert not found for invalid login.");
		}
	}

	@AfterMethod
	public void tearDownTest() throws InterruptedException {
		closeBrowser();
		ExtentReportManager.endTest();
	}
}
