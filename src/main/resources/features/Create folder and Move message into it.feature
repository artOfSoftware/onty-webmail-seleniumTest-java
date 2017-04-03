Feature: Create folder and move message into it

  A user should be able to create a custom folder
  and move message into it.

  Background:
    Given I am on the 'Login' page
    And   I enter valid username and valid password
    And   I have at least 1 message in my Inbox

  Scenario: Create custom folder and move message there
    When  I want to create a custom folder
    And   I enter folder name 'My Folder {rs}' and click Create Folder
    Then  I should find myself on 'Folder' page
    And   The folder should have 0 messages
    When  I want to see the Inbox folder
    And   I want to read the message #1 in the folder
    Then  I should find myself on 'Message' page
    When  I move the message to custom folder
    Then  I should find myself on 'Message' page
    And   The message should be in custom folder


