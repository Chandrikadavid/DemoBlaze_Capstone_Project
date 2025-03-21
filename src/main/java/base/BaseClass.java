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
import org.testng.annotations.AfterSuite;
import com.google.common.io.Files;

public class BaseClass {
	public static WebDriver driver; // Global WebDriver instance
	public static Properties prop;

	// Load Properties File
	public static void loadProperties() throws IOException {
		if (prop == null) {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(".\\src\\main\\java\\base\\data.properties");
			prop.load(fis);
		}
	}

	// Invoke Browser (Called from Test Classes)
	public static void invokeBrowser(String browser) throws IOException {
		if (driver == null) { // Ensures browser is initialized only once
			loadProperties();
			String browserName = (browser != null) ? browser : prop.getProperty("browser");

			if (browserName.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
			} else {
				throw new RuntimeException("Invalid browser name: " + browserName);
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(prop.getProperty("url"));
		}
	}

	// Quit Browser After All Tests
	@AfterSuite
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
			driver = null; // Reset driver after suite execution
		}
	}

	// Screenshot Method
	public static void screenshot(String filename) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		Files.copy(src, new File("./screenshots/" + filename + System.currentTimeMillis() + ".png"));
	}
}
