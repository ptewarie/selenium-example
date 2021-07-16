package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.UUID;

import static java.time.Duration.ofSeconds;


public class EditorPage {
  // Elements
  private final By editorTitle = By.cssSelector("textarea[data-test-id='editor-title']");
  private final By pageContent = (By.cssSelector("div[class*='ProseMirror']"));
  private final By publishButton = (By.id("publish-button"));
  private WebDriver driver;

  public EditorPage(WebDriver driver) {
    this.driver = driver;
  }


  // Actions
  public boolean isEditorPageLoaded() {
    FluentWait<WebDriver> wait = new FluentWait<>(driver);
    wait.withTimeout(ofSeconds(30));
    wait.pollingEvery(ofSeconds(1));
    wait.ignoring(NoSuchElementException.class);
    wait.until(ExpectedConditions.visibilityOfElementLocated(editorTitle));
    wait.until(ExpectedConditions.elementToBeClickable(editorTitle));
    return driver.findElement(editorTitle).isDisplayed();
  }

  public String setTitleOfPage() {
    String randomPageTitle = "PAGE_HEADER_TITLE_" + UUID.randomUUID();
    driver.findElement(editorTitle).sendKeys(randomPageTitle);
    return randomPageTitle;
  }

  public String setPageContent() {
    String randomPageContent = "PAGE_CONTENT_" + UUID.randomUUID();
    driver.findElement(pageContent).sendKeys(randomPageContent);
    return randomPageContent;
  }

  public PublishedDocumentPage clickOnPublishButton() {
    driver.findElement(publishButton).click();
    return new PublishedDocumentPage(driver);
  }


}
