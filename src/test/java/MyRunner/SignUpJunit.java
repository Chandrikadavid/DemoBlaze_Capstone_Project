package MyRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = ".\\src\\main\\java\\features\\signup.feature", glue = { "stepDefinations",
		"hooks" }, plugin = { "pretty" }, tags = "@signup", monochrome = false)
public class SignUpJunit {

}