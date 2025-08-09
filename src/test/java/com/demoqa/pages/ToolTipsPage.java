package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ToolTipsPage {
    private final WebDriver driver;

    @FindBy(id = "toolTipButton") private WebElement hoverButton;
    @FindBy(css = ".tooltip-inner") private WebElement tooltip;

    public ToolTipsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void hoverOverButton() {
        new Actions(driver).moveToElement(hoverButton).perform();
    }

    public String getTooltipText() {
        return tooltip.getText();
    }

    public boolean isTooltipDisplayed() {
        try {
            return tooltip.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
