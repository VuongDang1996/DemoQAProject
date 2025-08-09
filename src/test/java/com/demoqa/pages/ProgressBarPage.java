package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProgressBarPage {
    @FindBy(id = "startStopButton") private WebElement startButton;
    @FindBy(id = "progressBar") private WebElement progressBar;

    public ProgressBarPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickStartButton() {
        startButton.click();
    }

    public WebElement getProgressBar() {
        return progressBar;
    }

    public String getProgressValue() {
        return progressBar.getAttribute("aria-valuenow");
    }
}
