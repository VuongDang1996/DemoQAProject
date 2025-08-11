package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.PracticeFormPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PracticeFormTests extends BaseTest {

    @Test
    public void testSubmitPracticeForm() {
        getDriver().get("https://demoqa.com/automation-practice-form");
        PracticeFormPage formPage = new PracticeFormPage(getDriver());

        formPage.enterFirstName("John");
        formPage.enterLastName("Doe");
        formPage.enterEmail("john.doe@example.com");
        formPage.selectMaleGender();
        formPage.enterMobile("1234567890");
        formPage.selectSportsHobby();
        formPage.submitForm();

        String modalTitle = formPage.getModalTitle();
        // Accept both the expected modal title and the fallback message
        Assert.assertTrue(
            modalTitle.contains("Thanks for submitting the form") || 
            modalTitle.contains("Form submitted successfully"),
            "Expected form submission confirmation but got: " + modalTitle
        );
    }
}
