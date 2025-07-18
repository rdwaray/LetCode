@Table&DataHandling
Feature: Practice Table and Data
  Background:
    Given I am on the letcode homepage
  @TableAdvance
  Scenario: Table Interaction
    Given I go to Table test page
    When I search "Newport"
    Then I print the data
    And I change entries per page
    Then I print all the data

    @TableSimple
    Scenario: Table Assertion
      Given I go to Table Simple test page
      Then I assert Shopping List
      And I assert Let's handle it
      And I assert Sortable Tables

    @File
    Scenario: Download and Upload data
      Given I go to File test page
      Then I upload file
      And I download file
