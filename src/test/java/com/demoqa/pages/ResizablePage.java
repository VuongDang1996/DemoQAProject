package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResizablePage {
    private final WebDriver driver;

    @FindBy(css = "#resizableBoxWithRestriction .react-resizable-handle") private WebElement resizeHandle;
    @FindBy(id = "resizableBoxWithRestriction") private WebElement resizableBox;

    public ResizablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void resizeBox(int xOffset, int yOffset) {
        new Actions(driver).dragAndDropBy(resizeHandle, xOffset, yOffset).perform();
    }

    public String getBoxWidth() {
        return resizableBox.getCssValue("width");
    }

    public String getBoxHeight() {
        return resizableBox.getCssValue("height");
    }
}
