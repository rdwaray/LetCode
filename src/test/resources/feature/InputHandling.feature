@InputHandling
Feature: Practice Handling Input
  Background:
    Given I am on the letcode homepage
  @Input
  Scenario: Understanding Input Form Type
    Given I go to Input test page
    And  I fill full name with "Testing Jora"
    Then I click tab on keyboard
    And I extract text on text box
    And I clear text box
    And I confirm edit field is disabled
    And I confirm field is readonly

    @Button
    Scenario: Understanding Buttons Type
      Given I go to Button test page
      And I check Goto Home button details
      And I check Find Location button details
      And I check What is my color button details
      And I check How tall and fat am i button details
      And I check disabled button details
      And I check Hold button details

      @Dropdown
      Scenario: Dropdown input with different approach
        Given I go to Dropdown test page
        And I choose  values dropdown one with visible text
        Then I choose multiple values dropdown two
        When I choose last values of dropwdown three
        Then I print all the option
        And I choose values dropdown four by index

     @RadioButton
     Scenario: Check all radio buttons details
      Given I go to Radio test page
       Then I check Select any one
       And I check Cofirm you can select only one radio button
       And I check Find the bug
       And I check Find which one is selected
       And I check Confirm last field is disabled
       And I check Find if the checkbox is selected?
       And I check Accept the T&C

    @Calendar
    Scenario: Calendar input
      Given I go to Calendar test page
      Then I fill birthday date
