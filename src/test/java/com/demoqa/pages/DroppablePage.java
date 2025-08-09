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
        ElementUtils.dragAndDrop(driver, draggable, droppable);
        // Wait for drop animation
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getDroppableText() { 
        return droppable.getText(); 
    }
}
