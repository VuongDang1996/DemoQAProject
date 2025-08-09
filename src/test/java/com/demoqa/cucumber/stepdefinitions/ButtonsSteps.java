package com.demoqa.cucumber.stepdefinitions;

import com.demoqa.cucumber.CucumberBaseTest;
import com.demoqa.pages.ButtonsPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class ButtonsSteps {
    private ButtonsPage buttonsPage;

    @Given("I am on the DemoQA buttons page")
    public void i_am_on_the_demo_qa_buttons_page() {
        CucumberBaseTest.getDriver().get("https://demoqa.com/buttons");
        buttonsPage = new ButtonsPage(CucumberBaseTest.getDriver());
    }

    @When("I double click the double click button")
    public void i_double_click_the_double_click_button() {
        buttonsPage.doubleClickButton();
        // Add small wait for message to appear
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @When("I right click the right click button")
    public void i_right_click_the_right_click_button() {
        buttonsPage.rightClickButton();
        // Add small wait for message to appear
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @When("I click the dynamic click button")
    public void i_click_the_dynamic_click_button() {
        buttonsPage.clickDynamicButton();
        // Add small wait for message to appear
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Then("I should see the double click message")
    public void i_should_see_the_double_click_message() {
        String message = buttonsPage.getDoubleClickMessage();
        Assert.assertTrue(message.contains("double click") || message.contains("You have done a double click"),
            "Double click message should be displayed. Actual: " + message);
    }

    @Then("I should see the right click message")
    public void i_should_see_the_right_click_message() {
        String message = buttonsPage.getRightClickMessage();
        Assert.assertTrue(message.contains("right click") || message.contains("You have done a right click"),
            "Right click message should be displayed. Actual: " + message);
    }

    @Then("I should see the dynamic click message")
    public void i_should_see_the_dynamic_click_message() {
        String message = buttonsPage.getDynamicClickMessage();
        Assert.assertTrue(message.contains("dynamic click") || message.contains("You have done a dynamic click"),
            "Dynamic click message should be displayed. Actual: " + message);
    }
}
