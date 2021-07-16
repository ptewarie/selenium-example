package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.BaseUtil;

import java.util.concurrent.TimeUnit;

public class Hook extends BaseUtil {

  private final BaseUtil base;
  private WebDriver driver;
  String BASE_URL = "https://test.com/";

  public Hook(BaseUtil base) {
    this.base = base;
  }

  @Before
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver(getChromeOptions());
    driver.get(BASE_URL);
    driver.manage().window().fullscreen();
    driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    base.driver = driver;
  }

  private ChromeOptions getChromeOptions() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("disable-infobars");
    return options;
  }

  @After
  public void TearDown(Scenario scenario) {

    if (scenario.isFailed()) {
      takeScreenShot(scenario);
      System.out.println("Scenario : " + scenario.getName() + " has failed! ");
    } else {
      System.out.println("Scenario : " + scenario.getName() + " has passed" );
    }
    base.driver.quit();

    System.out.println("Report can be found under : target/cucumber-pretty/index.html");
  }


  public void takeScreenShot(Scenario scenario) {
    byte[] screenshot = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.BYTES);
    scenario.attach(screenshot, "image/png", scenario.getName());
  }

}
