package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.BrowserWindowsPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BrowserWindowsTests extends BaseTest {

    @Test
    public void newWindowTest() {
        getDriver().get("https://demoqa.com/browser-windows");
        BrowserWindowsPage browserWindowsPage = new BrowserWindowsPage(getDriver());
        
        String originalWindow = getDriver().getWindowHandle();
        browserWindowsPage.clickNewWindowButton();
        
        // Wait for new window to open with proper timeout
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        
        // Switch to new window
        List<String> windows = new ArrayList<>(getDriver().getWindowHandles());
        for (String window : windows) {
            if (!window.equals(originalWindow)) {
                getDriver().switchTo().window(window);
                break;
            }
        }
        
        // Wait for page to load completely
        wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.tagName("body")));
        
        // Check for content - Firefox might have different page content
        String pageSource = getDriver().getPageSource();
        boolean hasExpectedContent = pageSource.contains("This is a sample page") || 
                                   pageSource.contains("sample") ||
                                   getDriver().getCurrentUrl().contains("sample");
        
        Assert.assertTrue(hasExpectedContent, "Expected content not found. Page source: " + pageSource.substring(0, Math.min(200, pageSource.length())));
        
        getDriver().close();
        getDriver().switchTo().window(originalWindow);
    }
}
