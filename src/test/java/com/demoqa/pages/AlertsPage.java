package com.demoqa.pages;

import com.demoqa.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AlertsPage {
    private final WebDriver driver;

    @FindBy(id = "promtButton") private WebElement promptButton;
    @FindBy(id = "promptResult") private WebElement promptResult;

    public AlertsPage(WebDriver driver) { 
        this.driver = driver;
        PageFactory.initElements(driver, this); 
    }

    public void clickPromptButton() { 
        ElementUtils.safeClick(driver, promptButton);
    }
    
    public String getPromptResultText() { 
        return promptResult.getText(); 
    }
}
