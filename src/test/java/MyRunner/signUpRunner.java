package MyRunner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import base.BaseClass;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = ".\\src\\main\\java\\features\\signup.feature",
    glue = { "stepDefinations", "hooks" },
    plugin = { "pretty", "html:target/cucumber-reports.html" },
    tags = "@signup",
    monochrome = false
)
public class signUpRunner extends AbstractTestNGCucumberTests {

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) throws Exception {
        BaseClass.invokeBrowser(browser); // Initialize browser
    }
}
