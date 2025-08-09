package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.AlertsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AlertsTests extends BaseTest {

    @Test
    public void testPromptAlert() {
        getDriver().get("https://demoqa.com/alerts");
        AlertsPage alertsPage = new AlertsPage(getDriver());

        alertsPage.clickPromptButton();
        var alert = getDriver().switchTo().alert();
        String name = "Test Name";
        alert.sendKeys(name);
        alert.accept();

        Assert.assertTrue(alertsPage.getPromptResultText().contains(name));
    }
}
