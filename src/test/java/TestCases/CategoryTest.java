package TestCases;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.CategoryPage;
import utilities.ExtentReportManager;

public class CategoryTest extends BaseClass {
	CategoryPage categoryPage;

	@Parameters("browser") // Receive browser parameter from testng.xml
	@BeforeMethod
	public void setUp(String browser) throws IOException {
		invokeBrowser(browser); // Invoke browser from BaseClass
		categoryPage = new CategoryPage(driver);
	}

	@Test(priority = 1)
	public void testAddingProductsFromDifferentCategories() throws IOException {

		ExtentReportManager.startTest("Category Test");
		// Selecting "Laptops" and adding a product
		categoryPage.selectCategoryAndAddFirstProduct("Laptops");

		// Selecting "Phones" and adding a product
		categoryPage.selectCategoryAndAddFirstProduct("Phones");

		// Selecting "Monitors" and adding a product
		categoryPage.selectCategoryAndAddFirstProduct("Monitors");

		ExtentReportManager.logPass("Successfully added products from different categories.");
	}

	@AfterMethod
	public void tearDown() {
		closeBrowser(); // This is from BaseClass to close the browser after test
		ExtentReportManager.endTest();
	}
}
