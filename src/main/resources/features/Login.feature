Feature: Login

  User should be able to login using valid credentials.
  If using invalid credentials, no login should happen and an error message should be displayed.

  Background:
    Given I am on the 'Login' page

  Scenario: Login as valid user
    When  I enter valid credentials
    Then  I should find myself on 'Accounts Home' page

  Scenario: Try to login as invalid user
    When  I enter invalid credentials
    Then  I should find myself on 'Login' page
    And   I should see error message saying 'login unsuccessful'

