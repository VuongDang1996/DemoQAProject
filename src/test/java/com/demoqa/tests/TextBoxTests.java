package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.TextBoxPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TextBoxTests extends BaseTest {

    @DataProvider(name = "textBoxData", parallel = true)
    public Object[][] textBoxData() {
        return new Object[][]{
                {"John Doe", "john@test.com"},
                {"Jane Smith", "jane@test.com"},
                {"Alex Johnson", "alex@test.com"}
        };
    }

    @Test(dataProvider = "textBoxData")
    public void testFormSubmission(String name, String email) {
        getDriver().get("https://demoqa.com/text-box");
        TextBoxPage textBoxPage = new TextBoxPage(getDriver());
        textBoxPage.enterFullName(name);
        textBoxPage.enterEmail(email);
        textBoxPage.clickSubmit();
        String output = textBoxPage.getOutputText();
        Assert.assertTrue(output.contains("Name:" + name));
        Assert.assertTrue(output.contains("Email:" + email));
    }
}
