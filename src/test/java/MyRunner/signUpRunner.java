package MyRunner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import base.BaseClass;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
    features = "src/main/java/features/signup.feature",
    glue = { "stepDefinations", "hooks" },
    plugin = { "pretty", "html:target/cucumber-reports/cucumber.html"},
    tags = "@signup",
    monochrome = true
)
public class signUpRunner extends AbstractTestNGCucumberTests {

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) throws Exception {
        BaseClass.invokeBrowser(browser); // Initialize browser
    }
}
