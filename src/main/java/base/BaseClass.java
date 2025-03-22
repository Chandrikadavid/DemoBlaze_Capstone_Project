package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

import com.google.common.io.Files;

public class BaseClass {

	/***
	 * 
	 * ThreadLocal<WebDriver> is used to ensure that each test thread gets its own
	 * instance of WebDriver when running tests in parallel. This helps avoid
	 * conflicts and ensures thread safety.
	 */
	
	// Using ThreadLocal to ensure WebDriver is thread-safe for parallel execution
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public static Properties prop;

	public static void loadProperties() throws IOException {
		if (prop == null) {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(".\\src\\main\\java\\base\\data.properties");
			prop.load(fis);
		}
	}

	public static void invokeBrowser(String browser) throws IOException {
		loadProperties();

		if (browser.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver.set(new FirefoxDriver());
		} else if (browser.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
		} else {
			throw new RuntimeException("Invalid browser name: " + browser);
		}

		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().get(prop.getProperty("url"));
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void screenshot(String filename) throws IOException {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		Files.copy(src, new File("./screenshots/" + filename + System.currentTimeMillis() + ".png"));
	}

	@AfterClass // Close the browser after all test methods in the class
	public void closeBrowser() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
