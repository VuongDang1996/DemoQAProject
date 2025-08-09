@Buttons
Feature: Button Interactions
  As a user
  I want to interact with different types of buttons
  So that I can test various clicking mechanisms

  Background:
    Given I am on the DemoQA buttons page

  @Smoke
  Scenario: Double click button
    When I double click the double click button
    Then I should see the double click message

  @Smoke
  Scenario: Right click button
    When I right click the right click button
    Then I should see the right click message

  @Smoke
  Scenario: Dynamic click button
    When I click the dynamic click button
    Then I should see the dynamic click message
