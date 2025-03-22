package TestCases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.LoginPage;
import utilities.ExcelUtils;
import utilities.ExtentReportManager;

public class LoginTest extends BaseClass {
	LoginPage loginPage;

	@Parameters("browser") // Receive browser parameter from testng.xml
	@BeforeMethod
	public void setUp(String browser) throws IOException {
		invokeBrowser(browser);
		loginPage = new LoginPage(getDriver());
		ExtentReportManager.startTest("Login Test");
	}

	@Test(priority = 1)
	public void testValidLogin() throws InterruptedException, IOException {
		ExtentReportManager.logInfo("Starting Valid Login Test");

		loginPage.login(prop.getProperty("validUsername"), prop.getProperty("validPassword"));

		// Capture screenshot after login attempt
		screenshot("Valid Login");
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
			wait.until(ExpectedConditions.alertIsPresent());

			Alert alert = getDriver().switchTo().alert();
			String alertMessage = alert.getText();
			System.out.println("Alert Message: " + alertMessage);

			alert.accept();
			ExtentReportManager.logPass("Expected alert " + alertMessage);
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

		// Load invalid credentials from Excel
		String excelPath = "C:\\Users\\Windows\\Desktop\\AutomationData.xlsx";
		String sheetName = "DemoBlaze";
		ExcelUtils.loadExcel(excelPath, sheetName);
		List<String[]> credentials = ExcelUtils.getAllRowsData();

		for (String[] cred : credentials) {
			String username = cred[0];
			String password = cred[1];

			ExtentReportManager.logInfo("Testing invalid login with username: " + username);

			loginPage.login(username, password);
			screenshot("Invalid_Login_" + username);

			try {
				WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
				wait.until(ExpectedConditions.alertIsPresent());

				Alert alert = getDriver().switchTo().alert();
				String alertMessage = alert.getText();
				System.out.println("Alert Message: " + alertMessage);

				alert.accept();
				ExtentReportManager.logPass("Expected alert appeared: " + alertMessage);
			} catch (TimeoutException e) {
				ExtentReportManager.logFail("Expected alert not found for invalid login: " + username);
			}
		}

		// Close Excel file after execution
		ExcelUtils.closeExcel();
	}

	@AfterMethod
	public void tearDownTest() throws InterruptedException {
		closeBrowser();
		ExtentReportManager.endTest();
	}
}
