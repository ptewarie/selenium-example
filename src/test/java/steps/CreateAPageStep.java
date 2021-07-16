package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.EditorPage;
import pages.HomePage;
import pages.PublishedDocumentPage;
import pages.SpaceOverviewPage;
import utils.BaseUtil;

import static org.testng.Assert.*;

public class CreateAPageStep extends BaseUtil {

  private String pageTitle;
  private String pageContent;

  public CreateAPageStep(BaseUtil base) {
    this.driver = base.driver;
    this.spaceOverviewPage = new SpaceOverviewPage(driver);
    this.homePage = new HomePage(driver);
    this.publishedDocumentPage = new PublishedDocumentPage(driver);
    this.editorPage = new EditorPage(driver);
  }


  @And("the user creates a new page")
  public void theUserCreatesANewPage() {
    spaceOverviewPage.clickOnCreateButton();
    editorPage.isEditorPageLoaded();
    pageTitle = editorPage.setTitleOfPage();
    pageContent = editorPage.setPageContent();
    editorPage.clickOnPublishButton();
    assertTrue(publishedDocumentPage.isPublishedDocumentPageLoaded());
    publishedDocumentPage.getUrlPageCreated();
  }


  @Then("the user should be able to see the published page")
  public void theUserShouldBeAbleToSeeTheRestrictedPage() {
    publishedDocumentPage.navigateToPublishedPage();
    assertTrue(publishedDocumentPage.isPublishedDocumentPageLoaded());
    assertTrue(publishedDocumentPage.getTitleOfPublishedPage().contains(pageTitle));
    assertTrue(publishedDocumentPage.getContentOfPublishedPage().contains(pageContent));
  }

  @Then("the user should NOT be able to see the published page")
  public void theUserShouldNotBeAbleToSeeTheRestrictedPage() {
    publishedDocumentPage.navigateToPublishedPage();
    assertTrue(spaceOverviewPage.getRequestAccessView().contains("Request access"));
  }

  @Then("the user should not be able to edit the page")
  public void theUserShouldNotBeAbleToEditThePage() {
    publishedDocumentPage.navigateToPublishedPage();
    assertFalse(publishedDocumentPage.isEditButtonPresent());
  }


}
