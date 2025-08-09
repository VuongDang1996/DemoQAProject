package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracticeFormPage {
    private final WebDriver driver;

    @FindBy(id = "firstName") private WebElement firstNameInput;
    @FindBy(id = "lastName") private WebElement lastNameInput;
    @FindBy(id = "userEmail") private WebElement emailInput;
    @FindBy(xpath = "//label[@for='gender-radio-1']") private WebElement maleRadioLabel;
    @FindBy(id = "userNumber") private WebElement mobileInput;
    @FindBy(xpath = "//label[@for='hobbies-checkbox-1']") private WebElement sportsCheckboxLabel;
    @FindBy(id = "submit") private WebElement submitButton;
    @FindBy(id = "example-modal-sizes-title-lg") private WebElement modalTitle;

    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String firstName) { firstNameInput.sendKeys(firstName); }
    public void enterLastName(String lastName) { lastNameInput.sendKeys(lastName); }
    public void enterEmail(String email) { emailInput.sendKeys(email); }
    public void selectMaleGender() { maleRadioLabel.click(); }
    public void enterMobile(String mobile) { mobileInput.sendKeys(mobile); }
    public void selectSportsHobby() { sportsCheckboxLabel.click(); }
    public void submitForm() { new Actions(driver).moveToElement(submitButton).click().perform(); }

    public String getModalTitle() { return modalTitle.getText(); }
}
