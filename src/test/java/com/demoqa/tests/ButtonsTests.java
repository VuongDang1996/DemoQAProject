package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.ButtonsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ButtonsTests extends BaseTest {

    @Test
    public void doubleClickTest() {
        getDriver().get("https://demoqa.com/buttons");
        ButtonsPage buttonsPage = new ButtonsPage(getDriver());
        
        buttonsPage.doubleClickButton();
        try {
            Thread.sleep(2000); // Give time for message to appear
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String message = buttonsPage.getDoubleClickMessage();
        Assert.assertTrue(message.contains("double click") || message.contains("You have done a double click"));
    }

    @Test
    public void rightClickTest() {
        getDriver().get("https://demoqa.com/buttons");
        ButtonsPage buttonsPage = new ButtonsPage(getDriver());
        
        buttonsPage.rightClickButton();
        try {
            Thread.sleep(2000); // Give time for message to appear
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String message = buttonsPage.getRightClickMessage();
        Assert.assertTrue(message.contains("right click") || message.contains("You have done a right click"));
    }

    @Test
    public void dynamicClickTest() {
        getDriver().get("https://demoqa.com/buttons");
        ButtonsPage buttonsPage = new ButtonsPage(getDriver());
        
        buttonsPage.clickDynamicButton();
        try {
            Thread.sleep(2000); // Give time for message to appear
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        String message = buttonsPage.getDynamicClickMessage();
        Assert.assertTrue(message.contains("dynamic click") || message.contains("You have done a dynamic click"));
    }
}
