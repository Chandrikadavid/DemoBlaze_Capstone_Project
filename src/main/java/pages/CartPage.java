package pages;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	private WebDriver driver;
	private Properties prop;
	private WebDriverWait wait;

	// **Locators using Page Object Model**

	By cartButton = By.xpath("//a[@class='nav-link' and contains(text(),'Cart')]");
	By placeOrder = By.xpath("//button[contains(text(),'Place Order')]");
	By nameField = By.id("name");
	By countryField = By.id("country");
	By cityField = By.id("city");
	By cardField = By.id("card");
	By monthField = By.id("month");
	By yearField = By.id("year");
	By purchaseButton = By.xpath("//button[contains(text(),'Purchase')]");
	By successMessage = By.xpath("//h2[contains(text(),'Thank you for your purchase!')]");
	By cartItems = By.xpath("//tbody/tr"); // Cart item rows
	// By deleteButtons = By.xpath("//tbody/tr/td[4]/a"); // Delete button for each
	// cart item

	public CartPage(WebDriver driver, Properties prop) {
		this.driver = driver; // Assigns the WebDriver instance to the class variable 'driver'
		this.prop = prop; // Assigns the properties object to the class variable 'prop'
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initializes a WebDriverWait with a 10-second
																		// timeout for waiting on condition
	}

	// **Open Cart Page**
	public void openCart() {
		WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(cartButton));
		cartBtn.click();

	}

	// **Click 'Place Order'**
	public void clickPlaceOrder() {
		WebElement placeOrderBtn = wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
		placeOrderBtn.click(); // Click the "Place Order" button

	}

	// **Fill Order Details & Submit**
	public void placeOrder() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(prop.getProperty("name"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(countryField)).sendKeys(prop.getProperty("country"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(cityField)).sendKeys(prop.getProperty("city"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(cardField)).sendKeys(prop.getProperty("cardNumber"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(monthField)).sendKeys(prop.getProperty("month"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(yearField)).sendKeys(prop.getProperty("year"));

		wait.until(ExpectedConditions.elementToBeClickable(purchaseButton)).click();

	}

	// **Verify Order Success**
	public boolean verifyOrderPlaced() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
	}

	// **Get Cart Item Count**
	public int getCartItemCount() {
		try {
			List<WebElement> items = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cartItems));
			return items.size();
		} catch (TimeoutException e) {
			return 0; // Return 0 if no cart items are found
		}
	}

}
