package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import static java.time.Duration.ofSeconds;

public class HomePage {

  private final WebDriver driver;

  public HomePage(WebDriver driver) {
    this.driver = driver;
  }

  // Elements
  private final By spaceSectionTitle = By.cssSelector("div[data-testid='space-section']");
  private final By createButton = By.cssSelector("div[data-testid='create-button-wrapper']");
  private final By spaceNavigationDropdown = By.cssSelector("div[data-testid='app-navigation-primary-spaces-dropdown']");
  private final By updatedItems = By.cssSelector("div[class*='update-item-title ContentCard_updateItemTitle']");

  // Actions
  public boolean isHomePageLoaded() {
    FluentWait<WebDriver> wait = new FluentWait<>(driver);
    wait.withTimeout(ofSeconds(30));
    wait.pollingEvery(ofSeconds(1));
    wait.ignoring(NoSuchElementException.class);
    wait.until(ExpectedConditions.visibilityOfElementLocated(spaceSectionTitle));
    return driver.findElement(spaceSectionTitle).isDisplayed();
  }

  public EditorPage clickOnCreateButton() {
    driver.findElement(createButton).click();
    return new EditorPage(driver);
  }

  public SpaceOverviewPage clickOnSpaceNavigationDropdown() {
    driver.findElement(spaceNavigationDropdown).click();
    return new SpaceOverviewPage(driver);
  }

}
