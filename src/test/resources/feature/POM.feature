@PageObjectModel&Utilities
Feature: Practice POM
  Background:
    Given I am on the letcode homepage
  @Scrapedata
  Scenario: Take all product title and price
    Given I go to Fake Store test page
    And  I get all product titles and prices

@ValidasiItem
Scenario: Item validation, check their details
  Given I go to Fake Store test page
  And I click on firts product
  Then I get the product detail
  And I go back to product page
  Then I click on second product
  Then I get the product detail

  @Cart
  Scenario: Add product to cart and checkout
    Given I go to Fake Store test page
    When I go to login page
    Then I login with username "mor_2314" and password "83r5^_"
    And I add three product
    When I go to checkout page
    Then I delete the third product
    And I change the quantity of the first product
    And I click checkout button

  Scenario: Empty Cart
    Given I go to Fake Store test page
    When I go to checkout page
    Then I should see "Your cart is empty"
