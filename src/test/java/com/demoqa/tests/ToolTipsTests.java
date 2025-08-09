package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.ToolTipsPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ToolTipsTests extends BaseTest {

    @Test
    public void toolTipTest() {
        getDriver().get("https://demoqa.com/tool-tips");
        ToolTipsPage toolTipsPage = new ToolTipsPage(getDriver());
        
        toolTipsPage.hoverOverButton();
        
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
        wait.until(driver -> toolTipsPage.isTooltipDisplayed());
        
        Assert.assertTrue(toolTipsPage.isTooltipDisplayed());
        Assert.assertEquals(toolTipsPage.getTooltipText(), "You hovered over the Button");
    }
}
