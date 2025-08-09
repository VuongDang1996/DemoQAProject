package com.demoqa.cucumber.stepdefinitions;

import com.demoqa.cucumber.CucumberBaseTest;
import com.demoqa.pages.AlertsPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.Alert;
import org.testng.Assert;

public class AlertsSteps {
    private AlertsPage alertsPage;

    @Given("I am on the DemoQA alerts page")
    public void i_am_on_the_demo_qa_alerts_page() {
        CucumberBaseTest.getDriver().get("https://demoqa.com/alerts");
        alertsPage = new AlertsPage(CucumberBaseTest.getDriver());
    }

    @When("I click the prompt button")
    public void i_click_the_prompt_button() {
        alertsPage.clickPromptButton();
    }

    @When("I enter {string} in the alert")
    public void i_enter_in_the_alert(String text) {
        Alert alert = CucumberBaseTest.getDriver().switchTo().alert();
        alert.sendKeys(text);
    }

    @When("I accept the alert")
    public void i_accept_the_alert() {
        Alert alert = CucumberBaseTest.getDriver().switchTo().alert();
        alert.accept();
    }

    @Then("I should see {string} in the result message")
    public void i_should_see_in_the_result_message(String expectedText) {
        String resultText = alertsPage.getPromptResultText();
        Assert.assertTrue(resultText.contains(expectedText),
            "Result should contain: " + expectedText + ". Actual: " + resultText);
    }
}
