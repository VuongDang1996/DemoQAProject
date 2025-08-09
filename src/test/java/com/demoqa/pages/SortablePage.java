package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SortablePage {
    private final WebDriver driver;

    @FindBy(css = "#demo-tabpane-list .list-group-item") private List<WebElement> listItems;

    public SortablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void dragFirstItemToSecondPosition() {
        if (listItems.size() >= 2) {
            WebElement firstItem = listItems.get(0);
            WebElement secondItem = listItems.get(1);
            new Actions(driver).dragAndDrop(firstItem, secondItem).perform();
        }
    }

    public String getFirstItemText() {
        return listItems.get(0).getText();
    }

    public String getSecondItemText() {
        return listItems.get(1).getText();
    }
}
