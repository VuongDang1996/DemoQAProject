package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.TextBoxPage;
import com.demoqa.utils.JsonDataReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class TextBoxTests extends BaseTest {

    @DataProvider(name = "textBoxData", parallel = true)
    public Object[][] textBoxData() {
        return JsonDataReader.getTestData("textbox");
    }

    @Test(dataProvider = "textBoxData")
    public void testFormSubmission(Map<String, Object> testData) {
        String fullName = (String) testData.get("fullName");
        String email = (String) testData.get("email");
        String currentAddress = (String) testData.get("currentAddress");
        String permanentAddress = (String) testData.get("permanentAddress");
        
        getDriver().get("https://demoqa.com/text-box");
        TextBoxPage textBoxPage = new TextBoxPage(getDriver());
        
        textBoxPage.enterFullName(fullName);
        textBoxPage.enterEmail(email);
        textBoxPage.enterCurrentAddress(currentAddress);
        textBoxPage.enterPermanentAddress(permanentAddress);
        textBoxPage.clickSubmit();
        
        String output = textBoxPage.getOutputText();
        Assert.assertTrue(output.contains("Name:" + fullName), "Full name not found in output");
        Assert.assertTrue(output.contains("Email:" + email), "Email not found in output");
        Assert.assertTrue(output.contains("Current Address :" + currentAddress), "Current address not found in output");
        Assert.assertTrue(output.contains("Permananet Address :" + permanentAddress), "Permanent address not found in output");
    }
}
