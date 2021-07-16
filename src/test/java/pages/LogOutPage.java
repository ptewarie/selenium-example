package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.UUID;

import static java.time.Duration.ofSeconds;

public class LogOutPage {

  public LogOutPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements( driver, this);
  }

  private final WebDriver driver;

  // Elements
  private final By logOutButton = (By.id("logout-submit"));


  // Actions
  public boolean isLogOutPageLoaded() {
    FluentWait<WebDriver> wait = new FluentWait<>(driver);
    wait.withTimeout(ofSeconds(30));
    wait.pollingEvery(ofSeconds(1));
    wait.ignoring(NoSuchElementException.class);
    wait.until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
    wait.until(ExpectedConditions.elementToBeClickable(logOutButton));
    return driver.findElement(logOutButton).isDisplayed();
  }

  public void clickOnLogOutButton(){
    driver.findElement(logOutButton).click();
  }



}
