package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pages.HomePage;
import pages.LoginPage;
import pages.SpaceOverviewPage;
import utils.BaseUtil;
import utils.WindowManager;

import static org.testng.Assert.assertTrue;

public class LogInStep extends BaseUtil {

  String BASEURL = "https://test.com";


  public LogInStep(BaseUtil base) {
    this.driver = base.driver;
    this.loginPage = new LoginPage(driver);
    this.homePage = new HomePage(driver);
    this.spaceOverviewPage = new SpaceOverviewPage(driver);
    this.windowManager = new WindowManager(driver);
  }

  @Given("user {string} logs in with password {string}")
  public void userLogsInWithPassword(String username, String password) {
    loginPage.setUsername(username);
    loginPage.clickOnLoginButton();
    loginPage.setPassword(password);
    loginPage.clickOnLoginButton();
    assertTrue(homePage.isHomePageLoaded());
    windowManager.goTo(BASEURL+ "/wiki/spaces/MP/overview");
    assertTrue(spaceOverviewPage.isSpaceOverViewPageLoaded());
  }

  @And("another user logs with {string} and {string}")
  public void anotherUserLogsWithAnd(String username, String password) {
    loginPage.clearSession();
    windowManager.goTo(BASEURL);
    loginPage.setUsername(username);
    loginPage.clickOnLoginButton();
    loginPage.setPassword(password);
    loginPage.clickOnLoginButton();
    assertTrue(homePage.isHomePageLoaded());
  }

}
