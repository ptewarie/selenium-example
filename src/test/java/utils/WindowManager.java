package utils;

import org.openqa.selenium.WebDriver;

public class WindowManager {
  private final WebDriver driver;
  private final WebDriver.Navigation navigate;

  public WindowManager(WebDriver driver) {
    this.driver = driver;
    navigate = driver.navigate();
  }

  public void goBack() {
    navigate.back();
  }

  public void refreshPage() {
    navigate.refresh();
  }

  public void goTo(String url) {
    navigate.to(url);
  }
}
