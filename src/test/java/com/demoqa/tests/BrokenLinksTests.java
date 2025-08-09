package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.BrokenLinksPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrokenLinksTests extends BaseTest {

    @Test
    public void brokenLinkTest() {
        getDriver().get("https://demoqa.com/broken");
        BrokenLinksPage brokenLinksPage = new BrokenLinksPage(getDriver());
        
        brokenLinksPage.clickBrokenLink();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("status_codes/500"));
    }
}
