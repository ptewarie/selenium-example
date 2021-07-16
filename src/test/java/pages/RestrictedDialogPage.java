package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.time.Duration.ofSeconds;

public class RestrictedDialogPage {

  private final By restrictionsDialog = By.cssSelector("div[data-test-id='restrictions-dialog.content-mode-select']");
  private final By selectDropdown = By.cssSelector("div[id*='react-select']");
  private final By userAndGroupSearchField = By.cssSelector("div[data-test-id='user-and-group-search']");
  private final By dialogPanelNoRestrictions = By.cssSelector("div[data-test-id='restrictions-dialog.panel.no-restrictions-new']");
  private final By restrictionsDialogFooterButtons = By.cssSelector("footer button");
  private final By selectedDropdown = By.cssSelector("div[data-test-id='restrictions-dialog.content-mode-select']");
  private final By lockedIcon = By.cssSelector("img[data-test-id='locked-icon']");
  private final By unlockedIcon = By.cssSelector("img[data-test-id='unlocked-icon']");
  private final By autocompleteDropdown = By.cssSelector("div[id*='react-select-restrictions:user-and-group-search']");
  private final By restrictionDialogModal = By.cssSelector("div[data-test-id='restrictions-dialog-modal']");
  private final By restrictionsButton = By.cssSelector("button[data-test-id='restrictions.dialog.button']");

  private WebDriver driver;

  public RestrictedDialogPage(WebDriver driver) {
    this.driver = driver;
  }

  public boolean waitForRestrictionDialogToBeLoaded() {
    FluentWait<WebDriver> wait = new FluentWait<>(driver);
    wait.withTimeout(ofSeconds(30));
    wait.pollingEvery(ofSeconds(1));
    wait.ignoring(NoSuchElementException.class);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(restrictionsDialog));
    wait.until(ExpectedConditions.elementToBeClickable(restrictionsButton));
    return driver.findElement(restrictionsButton).isDisplayed();
  }

  public void waitAndClickOnRestrictedDialog() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(restrictionsDialog));
    driver.findElement(restrictionsDialog).click();
  }

  public void waitAndClickOnOptionFromDropdown(String text) {
    List<WebElement> elements = driver.findElements(selectDropdown);
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(selectDropdown, 2));
    for (WebElement element : elements) {
      if (element.getText().contains(text)) {
        element.click();
        break;
      }
    }


  }

  public boolean isOptionSelected(String text) {
    WebDriverWait wait;
    wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(selectedDropdown));
    return driver.findElement(selectedDropdown).getText().contains(text);
  }

  public boolean isNoRestrictionPanelDisplayed() {
    return driver.findElement(dialogPanelNoRestrictions).isDisplayed();
  }

  public void clickOnApplyButton() {
    List<WebElement> elements = driver.findElements(restrictionsDialogFooterButtons);
    clickOnElementWithText(elements, "Apply");
  }

  public void clickOnCancelButton() {
    List<WebElement> elements = driver.findElements(restrictionsDialogFooterButtons);
    clickOnElementWithText(elements, "Cancel");
  }

  public void clickOnElementWithText(List<WebElement> elements, String text) {
    for (WebElement element : elements) {
      if (element.getText().contains(text)) {
        element.click();
        break;
      }
    }
  }

  public void clickOnAutocompleteDropdownOption(String text){
    clickOnElementWithText(driver.findElements(autocompleteDropdown), text);
  }

  public void waitForDropDown(){
      FluentWait<WebDriver> wait = new FluentWait<>(driver);
      wait.withTimeout(ofSeconds(30));
      wait.pollingEvery(ofSeconds(1));
      wait.ignoring(NoSuchElementException.class);
      wait.until(ExpectedConditions.visibilityOfElementLocated(autocompleteDropdown));
  }


  public boolean isUnlockedIconVisible() {
    return isElementDisplayed(unlockedIcon);
  }

  public boolean isLockedIconVisible() {
    return isElementDisplayed(lockedIcon);
  }

  public boolean isElementDisplayed(By element){
    return driver.findElement(element).isDisplayed();

  }

  public void clickOnSearchField() {
    driver.findElement(userAndGroupSearchField).click();
  }

  public void clickOnCanEditDropdownFor(String name) {
    List<WebElement> tableRows = driver.findElements(By.cssSelector("tr td"));

    for (int i = 0; i < tableRows.size(); i++) {
      if (tableRows.get(i).getText().contains(name)) {
        tableRows.get(i + 1).click();
        break;
      }
    }
  }

  public void clickOnAddButton() {
    List<WebElement> button = driver.findElement(restrictionDialogModal).findElements(By.cssSelector("button"));
    clickOnElementWithText(button, "Add");
  }

}
