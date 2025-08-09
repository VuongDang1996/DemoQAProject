@WebTables
Feature: Web Tables Management
  As a user
  I want to manage data in web tables
  So that I can add, search, and verify table data

  Background:
    Given I am on the DemoQA web tables page

  @Smoke
  Scenario: Search for existing user
    When I search for "Cierra"
    Then I should see "Cierra" in the first row

  @Regression
  Scenario: Add new user to table
    Given I note the current number of table rows
    When I click the add button
    And I fill the form with the following data:
      | firstName  | lastName | email                | age | salary | department |
      | John       | Smith    | john.smith@test.com  | 30  | 50000  | Engineering|
    Then the table should have one more row than before

  @DataDriven
  Scenario Outline: Add multiple users
    When I click the add button
    And I add user with name "<firstName>" "<lastName>" and email "<email>"
    Then the user should be added successfully

    Examples:
      | firstName | lastName | email              |
      | Alice     | Johnson  | alice@example.com  |
      | Bob       | Williams | bob@example.com    |
      | Carol     | Brown    | carol@example.com  |
