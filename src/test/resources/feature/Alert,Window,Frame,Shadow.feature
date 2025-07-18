@InputHandling
Feature: Practice Handling Input
  Background:
    Given I am on the letcode homepage
  @Alert
  Scenario: Interact with different types of dialog boxes
    Given I go to Alert test page
    Then I interact with Accept the Alert
    And I interact with Dismiss the Alert & print the alert text
    And I interact with Type your name & accept
    And I interact with Sweet alert (Modal)

    @Window
    Scenario: Switch different types of tabs or windows
      Given I go to Window test page
      Then I click on Goto Home
      And I go back
      When I click on Open muiltple windows
      Then I Print the title of the page
      And I go back to main page
      And I close child page
    @Frame
    Scenario: Interact with different types of frames/iframes
      Given I go to Frame test page
      When I switch to parent frame
      Then I fill first name
      And I fill last name
      Then I switch to iframe
      And I fill email

      @Shadow
      Scenario: Interact with shadow DOM
        Given I go to Shadow DOM test page
        Then I interact with first element
        Then I interact with second element
        Then I interact with third element

