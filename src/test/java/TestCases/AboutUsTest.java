package TestCases;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import base.BaseClass;
import pages.AboutUsPage;
import utilities.ExtentReportManager;

public class AboutUsTest extends BaseClass {
    AboutUsPage aboutUsPage;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) throws IOException {
        invokeBrowser(browser);
        aboutUsPage = new AboutUsPage(getDriver()); // Use getDriver()
        ExtentReportManager.startTest("About Us Test");
    }

    @Test
    public void testAboutUsNavigation() throws IOException, InterruptedException {
        aboutUsPage.openAboutUs();
        screenshot("AboutUsPage");
        ExtentReportManager.logPass("Successfully navigated to the About Us page.");
    }

    @AfterMethod
    public void tearDownTest() {
        closeBrowser();
        ExtentReportManager.endTest();
    }
}
