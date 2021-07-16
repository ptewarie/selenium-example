package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;
import utils.BaseUtil;
import utils.WindowManager;

import static org.testng.Assert.assertTrue;

public class UpdateRestrictionStep extends BaseUtil {

  public UpdateRestrictionStep(BaseUtil base) {
    this.driver = base.driver;
    this.loginPage = new LoginPage(driver);
    this.spaceOverviewPage = new SpaceOverviewPage(driver);
    this.restrictedDialogPage = new RestrictedDialogPage(driver);
    this.publishedDocumentPage = new PublishedDocumentPage(driver);
    this.editorPage = new EditorPage(driver);
    this.homePage = new HomePage(driver);
    this.logOutPage = new LogOutPage(driver);
    this.windowManager = new WindowManager(driver);
  }

  @When("the user sets the permission to {string}")
  public void theUserSetsThePermissionTo(String permissionType) {
    restrictedDialogPage = publishedDocumentPage.clickOnRestrictionButton();
    restrictedDialogPage.waitAndClickOnRestrictedDialog();
    restrictedDialogPage.waitAndClickOnOptionFromDropdown(permissionType);
  }

  @Then("the page should show an unlocked icon")
  public void thePageShouldShowAnUnlockedIcon() {
    assertTrue(restrictedDialogPage.waitForRestrictionDialogToBeLoaded());
    assertTrue(restrictedDialogPage.isUnlockedIconVisible());
  }

  @Then("the page should show a locked icon")
  public void thePageShouldShowALockedIcon() {

  }

  @And("the user publishes those changes")
  public void theUserPublishesThoseChanges() {
    restrictedDialogPage.clickOnApplyButton();
  }

  @And("the user grants user {string} view and edit permissions")
  public void theUserGrantsUserViewAndEditPermissions(String name) {
    restrictedDialogPage.clickOnSearchField();
    restrictedDialogPage.waitForDropDown();
    restrictedDialogPage.clickOnAutocompleteDropdownOption(name);
    restrictedDialogPage.clickOnAddButton();
    restrictedDialogPage.clickOnApplyButton();
    assertTrue(restrictedDialogPage.waitForRestrictionDialogToBeLoaded());
    assertTrue(restrictedDialogPage.isLockedIconVisible());
    assertTrue(publishedDocumentPage.isPublishedDocumentPageLoaded());
  }

  @And("the dialog should show the no restrictions message")
  public void theDialogShouldShowTheNoRestrictionsMessage() {
    assertTrue(restrictedDialogPage.isNoRestrictionPanelDisplayed());
  }
}

//  @Test
//  public void testUpdateRestrictionToAnyoneCanViewAndEditOnParentPage() {
//    restrictedDialogPage = parentPage.clickOnRestrictionButton();
//    restrictedDialogPage.waitAndClickOnRestrictedDialog();
//    restrictedDialogPage.waitAndClickOnOptionFromDropdown("Anyone on testSite can view and edit");
//    restrictedDialogPage.waitAndClickOnRestrictedDialog();
//    assertTrue(restrictedDialogPage.isNoRestrictionPanelDisplayed());
//    restrictedDialogPage.clickOnApplyButton();
//    parentPage.isParentPageLoaded();
//    parentPage.clickOnRestrictionButton();
//    assertTrue(restrictedDialogPage.isOptionSelected("Anyone on testSite can view and edit"));
//    restrictedDialogPage.clickOnCancelButton();
//    assertTrue(restrictedDialogPage.isUnlockedIconVisible());
//
//  }
//
//  @Test
//  public void testUpdateRestrictionToSpecificPeopleOnParentPage() {
//    restrictedDialogPage = parentPage.clickOnRestrictionButton();
//    restrictedDialogPage.waitAndClickOnRestrictedDialog();
//    restrictedDialogPage.waitAndClickOnOptionFromDropdown("Only specific people can view and edit");
//    restrictedDialogPage.waitAndClickOnRestrictedDialog();
//    restrictedDialogPage.clickOnApplyButton();
//    parentPage.isParentPageLoaded();
//    parentPage.clickOnRestrictionButton();
//    assertTrue(restrictedDialogPage.isOptionSelected("Only specific people can view and edit"));
//    restrictedDialogPage.clickOnCancelButton();
//    assertTrue(restrictedDialogPage.isLockedIconVisible());
//  }
//
//  @Test
//  public void testUpdateUserToViewOnly() {
//    parentPage.clickOnRestrictionButton();
//    restrictedDialogPage.clickOnSearchField();
//    restrictedDialogPage.clickOnAutocompleteDropdownOption("testUser");
//    restrictedDialogPage.clickOnCanEditDropdownFor("testUser");
//    restrictedDialogPage.clickOnAddButton();
//    restrictedDialogPage.clickOnApplyButton();
//  }
