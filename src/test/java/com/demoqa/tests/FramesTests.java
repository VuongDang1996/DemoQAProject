package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.FramesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FramesTests extends BaseTest {

    @Test
    public void frameTest() {
        getDriver().get("https://demoqa.com/frames");
        FramesPage framesPage = new FramesPage(getDriver());
        
        framesPage.switchToFrame1();
        String headingText = framesPage.getFrameHeadingText();
        Assert.assertEquals(headingText, "This is a sample page");
        
        framesPage.switchToDefaultContent();
    }
}
