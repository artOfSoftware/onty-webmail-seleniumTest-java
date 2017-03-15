Feature: Send Message

  A user A should be able to send a message to another user B.
  This message should be placed into A's Sent box,
  and a copy be placed into B's Inbox.

  Background:
    Given I am on the 'Login' page
    And   I enter valid username and valid password
    And   I want to send a message

  Scenario: Send Message
    When  I enter valid new message information:
    | to       | subject    | text                    |
    | New User | Hello {rs} | This is my message {rs} |
    Then  I should find myself on 'Mailbox' page
    And   I should see message saying 'successfully sent'
    When  I want to see the Sent folder
    Then  I should find myself on 'Folder' page
    Then  I should see message listed
    When  I want to see the message
    Then  I should find myself on 'Message' page
    Then  I should see the details of the message
