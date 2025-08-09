package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextBoxPage {
    @FindBy(id = "userName") private WebElement fullNameInput;
    @FindBy(id = "userEmail") private WebElement emailInput;
    @FindBy(id = "submit") private WebElement submitButton;
    @FindBy(id = "output") private WebElement outputArea;

    public TextBoxPage(WebDriver driver) { PageFactory.initElements(driver, this); }
    public void enterFullName(String fullName) { fullNameInput.sendKeys(fullName); }
    public void enterEmail(String email) { emailInput.sendKeys(email); }
    public void clickSubmit() { submitButton.click(); }
    public String getOutputText() { return outputArea.getText(); }
}
