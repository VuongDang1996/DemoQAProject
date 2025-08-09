@Alerts
Feature: Alert Handling
  As a user
  I want to handle JavaScript alerts
  So that I can interact with pop-up dialogs

  Background:
    Given I am on the DemoQA alerts page

  @Smoke
  Scenario: Handle prompt alert
    When I click the prompt button
    And I enter "Test User" in the alert
    And I accept the alert
    Then I should see "Test User" in the result message

  @Regression
  Scenario: Handle prompt alert with different names
    When I click the prompt button
    And I enter "Cucumber Test" in the alert
    And I accept the alert
    Then I should see "Cucumber Test" in the result message
