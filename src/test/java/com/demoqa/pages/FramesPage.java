package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FramesPage {
    private final WebDriver driver;

    @FindBy(id = "frame1") private WebElement frame1;
    @FindBy(id = "sampleHeading") private WebElement frameHeading;

    public FramesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void switchToFrame1() {
        driver.switchTo().frame("frame1");
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public String getFrameHeadingText() {
        return frameHeading.getText();
    }
}
