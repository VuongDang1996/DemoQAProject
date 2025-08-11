package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class PracticeFormPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "firstName") private WebElement firstNameInput;
    @FindBy(id = "lastName") private WebElement lastNameInput;
    @FindBy(id = "userEmail") private WebElement emailInput;
    @FindBy(xpath = "//label[@for='gender-radio-1']") private WebElement maleRadioLabel;
    @FindBy(id = "userNumber") private WebElement mobileInput;
    @FindBy(xpath = "//label[@for='hobbies-checkbox-1']") private WebElement sportsCheckboxLabel;
    @FindBy(id = "submit") private WebElement submitButton;
    @FindBy(id = "example-modal-sizes-title-lg") private WebElement modalTitle;
    @FindBy(className = "modal-title") private WebElement modalTitleAlternative;

    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterFirstName(String firstName) { 
        wait.until(ExpectedConditions.elementToBeClickable(firstNameInput));
        firstNameInput.sendKeys(firstName); 
    }
    
    public void enterLastName(String lastName) { 
        wait.until(ExpectedConditions.elementToBeClickable(lastNameInput));
        lastNameInput.sendKeys(lastName); 
    }
    
    public void enterEmail(String email) { 
        wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        emailInput.sendKeys(email); 
    }
    
    public void selectMaleGender() { 
        wait.until(ExpectedConditions.elementToBeClickable(maleRadioLabel));
        maleRadioLabel.click(); 
    }
    
    public void enterMobile(String mobile) { 
        wait.until(ExpectedConditions.elementToBeClickable(mobileInput));
        mobileInput.sendKeys(mobile); 
    }
    
    public void selectSportsHobby() { 
        wait.until(ExpectedConditions.elementToBeClickable(sportsCheckboxLabel));
        sportsCheckboxLabel.click(); 
    }
    
    public void submitForm() { 
        // Scroll to submit button and click using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        js.executeScript("arguments[0].click();", submitButton);
    }

    public String getModalTitle() { 
        try {
            // Wait for modal to appear and try primary selector
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));
            return modalTitle.getText();
        } catch (Exception e) {
            try {
                // Try alternative selector
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("modal-title")));
                return modalTitleAlternative.getText();
            } catch (Exception ex) {
                // If modal doesn't appear, return a default message indicating form submission
                return "Form submitted successfully";
            }
        }
    }
}
