Feature: Create Account

  A new user should be able to signup (create an account on the system),
  and start using it right away.
  However, there are some limitations:
  - All user names must be unique
  - All email addresses must be unique
  - User must have a non empty length password

  Background:
    Given I am on the 'Login' page
    And   I want to signup

  Scenario: Create an account
    When  I enter valid new account information:
    | user name | full name | email             | password |
    | abc_{rs}  | Ab Bc {rs}| abc_{rs}@here.com | abc      |
    Then  I should find myself on 'Account' page
    And   I should see message saying 'created'

