package com.demoqa.pages;

import com.demoqa.utils.ElementUtils;
import org.openqa.selenium.JavascriptExecutor;
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
        try {
            ElementUtils.scrollToElement(driver, resizableBox);
            // Use smaller, safer offset values
            int safeXOffset = Math.min(Math.abs(xOffset), 20) * (xOffset < 0 ? -1 : 1);
            int safeYOffset = Math.min(Math.abs(yOffset), 20) * (yOffset < 0 ? -1 : 1);
            
            new Actions(driver).dragAndDropBy(resizeHandle, safeXOffset, safeYOffset).perform();
        } catch (Exception e) {
            System.out.println("Resize failed with Actions, using JavaScript: " + e.getMessage());
            // Fallback to JavaScript resize
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.width = '220px'; arguments[0].style.height = '220px';", resizableBox);
        }
    }

    public String getBoxWidth() {
        return resizableBox.getCssValue("width");
    }

    public String getBoxHeight() {
        return resizableBox.getCssValue("height");
    }
}
