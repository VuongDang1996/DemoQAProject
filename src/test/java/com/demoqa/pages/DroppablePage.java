package com.demoqa.pages;

import com.demoqa.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DroppablePage {
    private final WebDriver driver;

    @FindBy(id = "draggable") private WebElement draggable;
    @FindBy(id = "droppable") private WebElement droppable;

    public DroppablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void dragAndDrop() {
        ElementUtils.scrollToElement(driver, draggable);
        ElementUtils.waitForElement(driver, draggable, 10);
        ElementUtils.waitForElement(driver, droppable, 10);
        
        // Firefox-specific drag and drop handling
        String browserName = ((org.openqa.selenium.remote.RemoteWebDriver) driver)
            .getCapabilities().getBrowserName().toLowerCase();
        
        if (browserName.contains("firefox")) {
            // Firefox requires more explicit handling
            ElementUtils.dragAndDropWithRetry(driver, draggable, droppable);
        } else {
            ElementUtils.dragAndDrop(driver, draggable, droppable);
        }
        
        // Wait for drop animation and state change
        ElementUtils.waitForTextChange(driver, droppable, "Drop here", 10);
    }

    public String getDroppableText() { 
        return droppable.getText(); 
    }
}
