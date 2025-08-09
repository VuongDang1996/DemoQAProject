package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.LinksPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class LinksTests extends BaseTest {

    @Test
    public void homeLinktTest() {
        getDriver().get("https://demoqa.com/links");
        LinksPage linksPage = new LinksPage(getDriver());
        
        String originalWindow = getDriver().getWindowHandle();
        linksPage.clickHomeLink();
        
        // Wait for new window and switch to it
        List<String> windows = new ArrayList<>(getDriver().getWindowHandles());
        for (String window : windows) {
            if (!window.equals(originalWindow)) {
                getDriver().switchTo().window(window);
                break;
            }
        }
        
        Assert.assertTrue(getDriver().getCurrentUrl().contains("demoqa.com"));
        getDriver().close();
        getDriver().switchTo().window(originalWindow);
    }
}
