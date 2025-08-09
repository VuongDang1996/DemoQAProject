package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.BrowserWindowsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class BrowserWindowsTests extends BaseTest {

    @Test
    public void newWindowTest() {
        getDriver().get("https://demoqa.com/browser-windows");
        BrowserWindowsPage browserWindowsPage = new BrowserWindowsPage(getDriver());
        
        String originalWindow = getDriver().getWindowHandle();
        browserWindowsPage.clickNewWindowButton();
        
        // Wait for new window and switch to it
        List<String> windows = new ArrayList<>(getDriver().getWindowHandles());
        for (String window : windows) {
            if (!window.equals(originalWindow)) {
                getDriver().switchTo().window(window);
                break;
            }
        }
        
        Assert.assertTrue(getDriver().getPageSource().contains("This is a sample page"));
        getDriver().close();
        getDriver().switchTo().window(originalWindow);
    }
}
