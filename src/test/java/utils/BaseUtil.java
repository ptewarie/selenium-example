package utils;

import org.openqa.selenium.WebDriver;
import pages.*;


public class BaseUtil {

  public WebDriver driver;
  protected LoginPage loginPage;
  protected HomePage homePage;
  protected SpaceOverviewPage spaceOverviewPage;
  protected EditorPage editorPage;
  protected PublishedDocumentPage publishedDocumentPage;
  protected RestrictedDialogPage restrictedDialogPage;
  protected WindowManager windowManager;
  protected LogOutPage logOutPage;

}
