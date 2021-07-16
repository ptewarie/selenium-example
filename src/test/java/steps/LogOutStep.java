package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pages.*;
import utils.BaseUtil;
import utils.WindowManager;

import static org.testng.Assert.assertTrue;

public class LogOutStep extends BaseUtil {

  public LogOutStep(BaseUtil base) {
    this.driver = base.driver;
    this.logOutPage = new LogOutPage(driver);
    this.loginPage = new LoginPage(driver);
    this.windowManager = new WindowManager(driver);
  }

    @And("the user logs out")
  public void theUserLogsOut() {
    windowManager.goTo("https://test.com/logout");
    assertTrue(logOutPage.isLogOutPageLoaded());
    logOutPage.clickOnLogOutButton();
    assertTrue(loginPage.isLogInPageLoaded());
  }

}
