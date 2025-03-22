package MyRunner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.testng.annotations.Parameters;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import base.BaseClass;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/main/java/features/signup.feature",
    glue = { "stepDefinations", "hooks" },
    plugin = { "pretty", "html:target/cucumber-reports.html" },
    tags = "@signup",
    monochrome = false
)
public class signUpJunit {

	@Parameters("browser")
    @BeforeClass
    public static void setup() throws Exception {
        String browser = System.getProperty("browser");
        BaseClass.invokeBrowser(browser); // Initialize browser
    }
}

