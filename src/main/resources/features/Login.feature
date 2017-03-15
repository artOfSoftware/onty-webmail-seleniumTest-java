Feature: Login

  User should be able to login using valid credentials.
  If using invalid credentials, no login should happen and an error message should be displayed.

  Background:
    Given I am on the 'Login' page

  Scenario: Login as valid user
    When  I enter valid username and valid password
    Then  I should find myself on 'Account' page

  Scenario: Login as valid user with invalid password
    When  I enter valid username and invalid password
    Then  I should find myself on 'Login' page
    And   I should see error message saying 'login unsuccessful'

  Scenario: Try to login as invalid user
    When  I enter invalid username
    Then  I should find myself on 'Login' page
    And   I should see error message saying 'login unsuccessful'

