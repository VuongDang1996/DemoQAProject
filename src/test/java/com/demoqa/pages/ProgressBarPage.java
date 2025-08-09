package com.demoqa.pages;

import com.demoqa.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProgressBarPage {
    private final WebDriver driver;

    @FindBy(id = "startStopButton") private WebElement startButton;
    @FindBy(id = "progressBar") private WebElement progressBar;

    public ProgressBarPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickStartButton() {
        ElementUtils.safeClick(driver, startButton);
    }

    public WebElement getProgressBar() {
        return progressBar;
    }

    public String getProgressValue() {
        return progressBar.getAttribute("aria-valuenow");
    }
}
