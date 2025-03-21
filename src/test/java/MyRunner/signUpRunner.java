package MyRunner;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import base.BaseClass;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(features = ".\\src\\main\\java\\features\\signup.feature",
	glue = { "stepDefinations","hooks"},
	plugin = { "pretty","html:target/cucumber-reports.html"}, 
	tags = "@signup", 
	monochrome = false)
public class signUpRunner extends AbstractTestNGCucumberTests {
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) throws IOException {
		BaseClass.invokeBrowser(browser); // Initialize browser
	}
}

//package MyRunner;
//
//import org.junit.runner.RunWith;
//
//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(features = ".\\src\\main\\java\\features\\signup.feature",
//	glue = { "stepDefinations",	"hooks" },
//	plugin = { "pretty" },
//	tags = "@signup",
//	monochrome = false)
//public class signUpRunner{
//
//}