package com.demoqa.pages;

import com.demoqa.utils.ElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextBoxPage {
    private final WebDriver driver;

    @FindBy(id = "userName") private WebElement fullNameInput;
    @FindBy(id = "userEmail") private WebElement emailInput;
    @FindBy(id = "submit") private WebElement submitButton;
    @FindBy(id = "output") private WebElement outputArea;

    public TextBoxPage(WebDriver driver) { 
        this.driver = driver;
        PageFactory.initElements(driver, this); 
    }
    
    public void enterFullName(String fullName) { 
        ElementUtils.sendKeys(driver, fullNameInput, fullName);
    }
    
    public void enterEmail(String email) { 
        ElementUtils.sendKeys(driver, emailInput, email);
    }
    
    public void clickSubmit() { 
        ElementUtils.safeClick(driver, submitButton);
    }
    
    public String getOutputText() { 
        return outputArea.getText(); 
    }
}
