package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.ResizablePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResizableTests extends BaseTest {

    @Test
    public void resizableTest() {
        getDriver().get("https://demoqa.com/resizable");
        ResizablePage resizablePage = new ResizablePage(getDriver());
        
        String originalWidth = resizablePage.getBoxWidth();
        String originalHeight = resizablePage.getBoxHeight();
        
        resizablePage.resizeBox(50, 50);
        
        String newWidth = resizablePage.getBoxWidth();
        String newHeight = resizablePage.getBoxHeight();
        
        Assert.assertNotEquals(originalWidth, newWidth);
        Assert.assertNotEquals(originalHeight, newHeight);
    }
}
