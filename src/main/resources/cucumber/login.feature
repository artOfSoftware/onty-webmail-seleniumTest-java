Feature: Login

  Scenario: Login as valid user
    Given I am on the 'Login' page
    And   I enter valid credentials
    Then  I should go to 'Accounts Home' page

  Scenario: Login as invalid user
    Given I am on the 'Login' page
    And   I enter invalid credentials
    Then  I should go to 'Login' page
    Then  I should see error message

