package steps;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.     annotations.DataProvider;

@CucumberOptions(features = {"src/test/resources/features"} , plugin = {"pretty", "html:target/cucumber-pretty/index.html", "json:target/cucumber.json" },  glue = "steps")
public class TestRunner extends AbstractTestNGCucumberTests {

  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }

}