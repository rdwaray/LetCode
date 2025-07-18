@Form
Feature: Input Form
  Background:
    Given I am on the letcode homepage
  @FormInput
  Scenario: Input Form with CSV
    Given I go to Form test page
    Then I fill form