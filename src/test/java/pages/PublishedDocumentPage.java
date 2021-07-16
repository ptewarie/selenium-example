package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import static java.time.Duration.ofSeconds;

public class PublishedDocumentPage {

  private final WebDriver driver;
  private final WebDriver.Navigation navigate;
  // Elements
  private final By renderedDocument = (By.cssSelector("div[class*='ak-renderer-document']"));
  private final By pageContentHeader = By.cssSelector("div[data-test-id='page-content-header']");
  private final By pageContentOnly = By.cssSelector("div[data-test-id='page-content-only']");
  private final By publishButton = (By.id("publish-button"));
  private final By restrictionsButton = By.cssSelector("button[data-test-id='restrictions.dialog.button']");
  private final By restrictionsDialog = By.cssSelector("div[data-test-id='restrictions-dialog.content-mode-select']");
  private final By editButton = By.cssSelector("div[data-test-id='fabric-edit-button']");
  private String pathOfPublishedPage;

  public PublishedDocumentPage(WebDriver driver) {
    this.driver = driver;
    navigate = driver.navigate();

  }

  // Actions
  public boolean isPublishedDocumentPageLoaded() {
    FluentWait<WebDriver> wait = new FluentWait<>(driver);
    wait.withTimeout(ofSeconds(30));
    wait.pollingEvery(ofSeconds(1));
    wait.ignoring(NoSuchElementException.class);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(publishButton));
    wait.until(ExpectedConditions.elementToBeClickable(restrictionsButton));
    return driver.findElement(restrictionsButton).isDisplayed();
  }

  public RestrictedDialogPage clickOnRestrictionButton() {
    driver.findElement(restrictionsButton).click();
    return new RestrictedDialogPage(driver);
  }

  public void getUrlPageCreated() {
    pathOfPublishedPage = driver.getCurrentUrl();
  }

  public void navigateToPublishedPage() {
    navigate.to(pathOfPublishedPage);
  }

  public String getTitleOfPublishedPage() {
    return driver.findElement(pageContentHeader).getText();
  }

  public String getContentOfPublishedPage() {
    return driver.findElement(pageContentOnly).getText();
  }

  public boolean isEditButtonPresent(){
    return driver.findElements(editButton).size() > 0;
  }

}
