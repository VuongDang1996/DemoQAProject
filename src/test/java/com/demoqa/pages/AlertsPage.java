package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertsPage {
    @FindBy(id = "promtButton") private WebElement promptButton;
    @FindBy(id = "promptResult") private WebElement promptResult;

    public AlertsPage(WebDriver driver) { PageFactory.initElements(driver, this); }

    public void clickPromptButton() { promptButton.click(); }
    public String getPromptResultText() { return promptResult.getText(); }
}
