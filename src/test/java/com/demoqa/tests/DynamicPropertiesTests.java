package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.DynamicPropertiesPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DynamicPropertiesTests extends BaseTest {

    @Test
    public void enableAfterDelayTest() {
        getDriver().get("https://demoqa.com/dynamic-properties");
        DynamicPropertiesPage dynamicPropertiesPage = new DynamicPropertiesPage(getDriver());
        
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dynamicPropertiesPage.getEnableAfterButton()));
        
        Assert.assertTrue(dynamicPropertiesPage.isButtonEnabled());
    }
}
