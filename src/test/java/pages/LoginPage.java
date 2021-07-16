package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseUtil;
import utils.WindowManager;

import static java.time.Duration.ofSeconds;

public class LoginPage {

  private WebDriver driver;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  // Elements
  private final By usernameField = By.id("username");
  private final By passwordField = By.id("password");
  private final By loginButton = By.id("login-submit");

  // Actions
  public void clearSession() {
    driver.manage().deleteAllCookies();
  }

  public void setUsername(String username) {
    driver.findElement(usernameField).clear();
    driver.findElement(usernameField).sendKeys(username);
  }

  public boolean isLogInPageLoaded() {
    FluentWait<WebDriver> wait = new FluentWait<>(driver);
    wait.withTimeout(ofSeconds(30));
    wait.pollingEvery(ofSeconds(1));
    wait.ignoring(NoSuchElementException.class);
    wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
    wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    return driver.findElement(loginButton).isDisplayed();
  }

  public void setPassword(String password) {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
    driver.findElement(passwordField).clear();
    driver.findElement(passwordField).sendKeys(password);
  }

  public void clickOnLoginButton() {
    driver.findElement(loginButton).click();
  }

}
