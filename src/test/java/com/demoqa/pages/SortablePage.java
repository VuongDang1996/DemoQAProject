package com.demoqa.pages;

import com.demoqa.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
            ElementUtils.dragAndDrop(driver, firstItem, secondItem);
            // Wait for sort animation
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public String getFirstItemText() {
        // Refresh the list after sorting
        listItems = driver.findElements(org.openqa.selenium.By.cssSelector("#demo-tabpane-list .list-group-item"));
        return listItems.get(0).getText();
    }

    public String getSecondItemText() {
        // Refresh the list after sorting
        listItems = driver.findElements(org.openqa.selenium.By.cssSelector("#demo-tabpane-list .list-group-item"));
        return listItems.get(1).getText();
    }
}
