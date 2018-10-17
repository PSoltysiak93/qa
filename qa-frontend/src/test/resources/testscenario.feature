Feature: Test of windows switch to

  @window @windowtest
  Scenario: Switch to other window
    Given User is one main page
    When User press link
    Then User is switch to other window
