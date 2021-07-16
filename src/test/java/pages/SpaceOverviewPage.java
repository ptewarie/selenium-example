package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import static java.time.Duration.ofSeconds;

public class SpaceOverviewPage {

  private final WebDriver driver;

  // Elements
  private final By titleText = By.id("title-text");
  private final By createButton = By.cssSelector("div[data-testid='create-button-wrapper']");
  private final By spaceViewPage = (By.cssSelector(".wrapper-space-view-page"));

  public SpaceOverviewPage(WebDriver driver) {
    this.driver = driver;
  }

  // Actions
  public boolean isSpaceOverViewPageLoaded() {
    FluentWait<WebDriver> wait = new FluentWait<>(driver);
    wait.withTimeout(ofSeconds(30));
    wait.pollingEvery(ofSeconds(1));
    wait.ignoring(NoSuchElementException.class);
    wait.until(ExpectedConditions.visibilityOfElementLocated(titleText));
    wait.until(ExpectedConditions.elementToBeClickable(createButton));
    return driver.findElement(titleText).isDisplayed();
  }

  public String getRequestAccessView(){
    return driver.findElement(spaceViewPage).getText();
  }

  public void clickOnCreateButton() {
    driver.findElement(createButton).click();
  }

}
