# UI Test Automation for testSite

This repository contains UI automated functional tests for the testSite permission feature using Selenium, TestNG and
Cucumber. It also contains some examples on how to write you tests.

Test scenarios are described in the feature files located here
`./src/test/resources/features/PagePermissionForEveryone.feature.`

## Installation ##

You need to have Java installed along with Maven.

For this example you can
use [Java 8 JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
and [Maven](https://maven.apache.org/download.cgi).

To install dependencies run:
```console
$ mvn install
```
##  Running Tests
```console
mvn test
```
You can also select specific scenarios to execute using `-Dcucumber.options="--tags @your_tag" `

## Assumptions

These are assumptions I have made in order to complete this exercise. Some of these assumptions won't necessarily hold
up in practice.

- chrome is the only browser used
- the focus is E2E tests

## Selectors

For the tests I have mostly used CSS selectors and

### Avoid using Xpath

- Xpath engines are different in each browser, hence make them inconsistent
- IE does not have a native xpath engine, therefore selenium injects its own xpath engine for compatibility of its API.
  Thus losing the advantage of using native browser features.
- Xpath tend to become complex and hence make hard to read.

## How to write a UI test

- Create a new feature in `test/java/resources/features` under a sub-folder named as per your feature.
- Name your feature in the format `{Descriptive Feature Name}Feature`
- Create you glue code for these features under steps in `test/java/steps`

So your step will start to look like this:

  ```java
  @Given("user {string} logs in with password {string}")
public void userLogsInWithPassword(String username,String password){
    loginPage.setUsername(username);
    loginPage.clickOnLoginButton();
    loginPage.setPassword(password);
    loginPage.clickOnLoginButton();
    assertTrue(spaceOverviewPage.isSpaceOverViewPageLoaded());
    }
  ```

## How to write a UI screen

- We are using the "Page Object Model" in this format:

```java
package pages;

public class LoginPage {

  private final WebDriver driver;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  // Elements
  private final By usernameField = By.id("username");
  private final By passwordField = By.id("password");
  private final By loginButton = By.id("login-submit");

  // Actions
  public void setUsername(String username) {
    driver.findElement(usernameField).clear();
    driver.findElement(usernameField).sendKeys(username);
  }

}
  ```

## Wait Strategies

There are different types of waiting strategies:

- An Explicit Waits will instruct the WebDriver to wait until certain a condition occurs before proceeding with
  executing the code

```java
public class RestrictedDialogPage {
  public void waitAndClickOnRestrictedDialog() {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(restrictionsDialog));
    driver.findElement(restrictionsDialog).click();
  }
}
```

- Fluent Wait looks for a web element repeatedly at regular intervals until timeout happens or until the object is
  found.

```java
public class RestrictedDialogPage {
  public boolean isParentPageLoaded() {
    FluentWait<WebDriver> wait = new FluentWait<>(driver);
    wait.withTimeout(ofSeconds(30));
    wait.pollingEvery(ofSeconds(1));
    wait.ignoring(NoSuchElementException.class);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(restrictionsDialog));
    wait.until(ExpectedConditions.elementToBeClickable(restrictionsButton));
    return driver.findElement(restrictionsButton).isDisplayed();
  }
}
```

- In Implicit Waits directs the WebDriver to wait for a certain measure of time before throwing an exception. We
  generally, want to wait these types of waits

## Reports

An html report will be generated after each test run under:

`target/cucumber-pretty/index.html`