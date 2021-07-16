@UpdatePagePermissions
Feature:  As an user,
  I want to be able to change restrictions for a page
  So that I can decide who can view or edit the page

  Scenario Outline: Set restrictions so that everyone on testSite can see the page
    Given user "<username_a>" logs in with password "<password>"
    And the user creates a new page
    When the user sets the permission to "<permission_type>"
    And the dialog should show the no restrictions message
    And the user publishes those changes
    And the page should show an unlocked icon
    And the user logs out
    And another user logs with "<username_b>" and "<password>"
    Then the user should be able to see the published page
    Examples:
      | username_a | username_b | password | permission_type |
      | *******    | *******    | *******  | *******         |

  Scenario Outline: Set restrictions so that some on testSite can edit the page
    Given user "<username_a>" logs in with password "<password>"
    And the user creates a new page
    When the user sets the permission to "<permission_type>"
    And the user publishes those changes
    And the page should show an unlocked icon
    And the user logs out
    And another user logs with "<username_b>" and "<password>"
    Then the user should be able to see the published page
    And the user should not be able to edit the page
    Examples:
      | username_a | username_b | password | permission_type |
      | *******    | *******    | *******  | *******         |

  Scenario Outline: Set restrictions so that a specific person can see the page
    Given user "<username_a>" logs in with password "<password>"
    And the user creates a new page
    When the user sets the permission to "<permission_type>"
    And the user grants user "<user>" view and edit permissions
    And the user logs out
    And another user logs with "<username_b>" and "<password>"
    Then the user should be able to see the published page
    Examples:
      | username_a | username_b | password | user    | permission_type |
      | *******    | *******    | *******  | ******* | *******         |

  Scenario Outline: Set restrictions so that a specific person cannot see the page
    Given user "<username_a>" logs in with password "<password>"
    And the user creates a new page
    When the user sets the permission to "<permission_type>"
    And the user publishes those changes
    And the user logs out
    And another user logs with "<username_b>" and "<password>"
    Then the user should NOT be able to see the published page
    Examples:
      | username_a | username_b | password | user    | permission_type |
      | *******    | *******    | *******  | ******* | *******         |