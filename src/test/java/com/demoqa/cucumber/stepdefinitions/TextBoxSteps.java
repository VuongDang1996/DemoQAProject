package com.demoqa.cucumber.stepdefinitions;

import com.demoqa.cucumber.CucumberBaseTest;
import com.demoqa.pages.TextBoxPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class TextBoxSteps {
    private TextBoxPage textBoxPage;

    @Given("I am on the DemoQA text box page")
    public void i_am_on_the_demo_qa_text_box_page() {
        CucumberBaseTest.getDriver().get("https://demoqa.com/text-box");
        textBoxPage = new TextBoxPage(CucumberBaseTest.getDriver());
    }

    @When("I enter full name {string}")
    public void i_enter_full_name(String fullName) {
        textBoxPage.enterFullName(fullName);
    }

    @When("I enter email {string}")
    public void i_enter_email(String email) {
        textBoxPage.enterEmail(email);
    }

    @When("I enter current address {string}")
    public void i_enter_current_address(String address) {
        // Add current address method to TextBoxPage if needed
        System.out.println("Current address: " + address);
    }

    @When("I enter permanent address {string}")
    public void i_enter_permanent_address(String address) {
        // Add permanent address method to TextBoxPage if needed
        System.out.println("Permanent address: " + address);
    }

    @When("I click the submit button")
    public void i_click_the_submit_button() {
        textBoxPage.clickSubmit();
    }

    @Then("I should see the submitted information displayed")
    public void i_should_see_the_submitted_information_displayed() {
        String output = textBoxPage.getOutputText();
        Assert.assertFalse(output.isEmpty(), "Output should not be empty");
    }

    @Then("the name should be {string}")
    public void the_name_should_be(String expectedName) {
        String output = textBoxPage.getOutputText();
        Assert.assertTrue(output.contains("Name:" + expectedName), 
            "Output should contain name: " + expectedName);
    }

    @Then("the email should be {string}")
    public void the_email_should_be(String expectedEmail) {
        String output = textBoxPage.getOutputText();
        Assert.assertTrue(output.contains("Email:" + expectedEmail), 
            "Output should contain email: " + expectedEmail);
    }
}
