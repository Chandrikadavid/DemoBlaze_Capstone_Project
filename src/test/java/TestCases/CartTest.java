package TestCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.*;

import base.BaseClass;
import pages.CartPage;
import utilities.ExtentReportManager;

public class CartTest extends BaseClass {
    private CartPage cartPage;

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) throws IOException {
        invokeBrowser(browser); // Start browser only once
        cartPage = new CartPage(getDriver(), prop);
    }

    @Test(priority = 1)
    public void testOpenCart() throws IOException {
        ExtentReportManager.startTest("Cart Test");
        cartPage.openCart();
        ExtentReportManager.logInfo("Opened Cart page");
    }

    @Test(priority = 2, dependsOnMethods = "testOpenCart")
    public void testPlaceOrder() throws IOException {
        ExtentReportManager.startTest("Place Order Test");
        cartPage.clickPlaceOrder();
        cartPage.placeOrder();
        Assert.assertTrue(cartPage.verifyOrderPlaced(), "Order placement failed!");
    }

    @AfterClass
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit(); // Close the browser session at the end
        }
        ExtentReportManager.endTest();
    }
}
