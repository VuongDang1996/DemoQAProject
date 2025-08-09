package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrowserWindowsPage {
    @FindBy(id = "windowButton") private WebElement newWindowButton;
    @FindBy(id = "tabButton") private WebElement newTabButton;

    public BrowserWindowsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickNewWindowButton() {
        newWindowButton.click();
    }

    public void clickNewTabButton() {
        newTabButton.click();
    }
}
