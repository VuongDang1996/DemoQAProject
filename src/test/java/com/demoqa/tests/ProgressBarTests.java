package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.ProgressBarPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProgressBarTests extends BaseTest {

    @Test
    public void progressBarTest() {
        getDriver().get("https://demoqa.com/progress-bar");
        ProgressBarPage progressBarPage = new ProgressBarPage(getDriver());
        
        progressBarPage.clickStartButton();
        
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(driver -> progressBarPage.getProgressValue().equals("100"));
        
        Assert.assertEquals(progressBarPage.getProgressValue(), "100");
    }
}
