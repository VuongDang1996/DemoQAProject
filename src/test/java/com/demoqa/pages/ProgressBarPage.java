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

    @FindBy(id = "startStopButton") private WebElement startButton;
    @FindBy(id = "progressBar") private WebElement progressBar;

    public ProgressBarPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickStartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(startButton));
        ElementUtils.safeClick(driver, startButton);
    }

    public WebElement getProgressBar() {
        wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.id("progressBar")));
        return progressBar;
    }

    public String getProgressValue() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(org.openqa.selenium.By.id("progressBar")));
            String value = progressBar.getAttribute("aria-valuenow");
            return value != null ? value : "0";
        } catch (Exception e) {
            return "0";
        }
    }
}
