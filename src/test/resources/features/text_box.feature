@TextBox
Feature: Text Box Form Functionality
  As a user
  I want to fill out a text box form
  So that I can submit my information

  Background:
    Given I am on the DemoQA text box page

  @Smoke @Regression
  Scenario: Submit text box form with valid data
    When I enter full name "John Doe"
    And I enter email "john.doe@example.com"
    And I enter current address "123 Main Street, New York"
    And I enter permanent address "456 Oak Avenue, California"
    And I click the submit button
    Then I should see the submitted information displayed
    And the name should be "John Doe"
    And the email should be "john.doe@example.com"

  @DataDriven
  Scenario Outline: Submit form with multiple users
    When I enter full name "<fullName>"
    And I enter email "<email>"
    And I click the submit button
    Then I should see the submitted information displayed
    And the name should be "<fullName>"

    Examples:
      | fullName    | email              |
      | Alice Smith | alice@example.com  |
      | Bob Johnson | bob@example.com    |
      | Carol Davis | carol@example.com  |
