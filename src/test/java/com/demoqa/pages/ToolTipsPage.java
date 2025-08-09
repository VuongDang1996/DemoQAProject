package com.demoqa.pages;

import com.demoqa.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ToolTipsPage {
    private final WebDriver driver;

    @FindBy(id = "toolTipButton") private WebElement hoverButton;
    @FindBy(css = ".tooltip-inner") private WebElement tooltip;

    public ToolTipsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void hoverOverButton() {
        ElementUtils.moveToElement(driver, hoverButton);
        // Wait a bit for tooltip to appear
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getTooltipText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOf(tooltip));
            return tooltip.getText();
        } catch (Exception e) {
            return "Tooltip not found";
        }
    }

    public boolean isTooltipDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOf(tooltip));
            return tooltip.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
