package TestCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.CartPage;
import pages.CategoryPage;
import utilities.ExtentReportManager;

public class CategoryTest extends BaseClass {
	CategoryPage categoryPage;
	CartPage cartPage;

	@Parameters("browser") // Receive browser parameter from testng.xml
	@BeforeMethod
	public void setUp(String browser) throws IOException {
		invokeBrowser(browser); // Invoke browser from BaseClass
		categoryPage = new CategoryPage(getDriver());
		cartPage = new CartPage(getDriver(), prop);
	}

	@Test(priority = 1)
	public void testAddingProductsFromDifferentCategories() throws IOException, InterruptedException {

		ExtentReportManager.startTest("Category Test");
		// Selecting "Laptops" and adding a product
		categoryPage.selectCategoryAndAddFirstProduct("Laptops");

		// Selecting "Phones" and adding a product
		categoryPage.selectCategoryAndAddFirstProduct("Phones");

		// Selecting "Monitors" and adding a product
		categoryPage.selectCategoryAndAddFirstProduct("Monitors");

		// Going to the Cart to check wheather products are added or not
		categoryPage.goToCart();
		Thread.sleep(2000);

		ExtentReportManager.logPass("Successfully added products from different categories.");
	}

	@AfterMethod
	public void tearDown() {
		closeBrowser(); // This is from BaseClass to close the browser after test
		ExtentReportManager.endTest();
	}
}
