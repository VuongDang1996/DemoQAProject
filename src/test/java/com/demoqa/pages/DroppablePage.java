package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
        new Actions(driver).dragAndDrop(draggable, droppable).perform();
    }

    public String getDroppableText() { return droppable.getText(); }
}
