package com.demoqa.pages;

import com.demoqa.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ProgressBarPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "startStopButton") private WebElement startStopButton;
    @FindBy(id = "resetButton") private WebElement resetButton;
    @FindBy(css = "div[role='progressbar']") private WebElement progressBar;
    @FindBy(className = "progress-bar") private WebElement progressBarElement;

    public ProgressBarPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickStartButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(org.openqa.selenium.By.id("startStopButton")));
            ElementUtils.safeClick(driver, startStopButton);
        } catch (Exception e) {
            System.out.println("Start button not found or not clickable: " + e.getMessage());
        }
    }

    public WebElement getProgressBar() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.cssSelector("div[role='progressbar']")));
            return progressBar;
        } catch (Exception e) {
            try {
                // Fallback to class-based selector
                wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.className("progress-bar")));
                return progressBarElement;
            } catch (Exception ex) {
                System.out.println("Progress bar not found: " + ex.getMessage());
                return null;
            }
        }
    }

    public String getProgressValue() {
        try {
            WebElement progressElement = getProgressBar();
            if (progressElement != null) {
                String value = progressElement.getAttribute("aria-valuenow");
                System.out.println("Progress value from aria-valuenow: " + value);
                return value != null ? value : "0";
            }
            return "0";
        } catch (Exception e) {
            System.out.println("Error getting progress value: " + e.getMessage());
            return "0";
        }
    }

    public boolean isProgressComplete() {
        try {
            // Check if Reset button is present (indicates completion)
            wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.id("resetButton")));
            return resetButton.isDisplayed();
        } catch (Exception e) {
            // Also check if progress value is 100
            try {
                String progressValue = getProgressValue();
                return "100".equals(progressValue);
            } catch (Exception ex) {
                System.out.println("Error checking completion: " + ex.getMessage());
                return false;
            }
        }
    }

    public String getButtonText() {
        try {
            // Try reset button first
            if (isElementPresent(org.openqa.selenium.By.id("resetButton"))) {
                return resetButton.getText();
            }
            // Then try start/stop button
            if (isElementPresent(org.openqa.selenium.By.id("startStopButton"))) {
                return startStopButton.getText();
            }
            return "";
        } catch (Exception e) {
            System.out.println("Error getting button text: " + e.getMessage());
            return "";
        }
    }
    
    private boolean isElementPresent(org.openqa.selenium.By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
