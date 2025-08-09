package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DynamicPropertiesPage {
    @FindBy(id = "enableAfter") private WebElement enableAfterButton;

    public DynamicPropertiesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getEnableAfterButton() {
        return enableAfterButton;
    }

    public boolean isButtonEnabled() {
        return enableAfterButton.isEnabled();
    }
}
