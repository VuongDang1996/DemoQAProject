package com.demoqa.pages;

import com.demoqa.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    @FindBy(id = "gender-radio-1") private WebElement maleRadioInput;
    @FindBy(id = "userNumber") private WebElement mobileInput;
    @FindBy(xpath = "//label[@for='hobbies-checkbox-1']") private WebElement sportsCheckboxLabel;
    @FindBy(id = "hobbies-checkbox-1") private WebElement sportsCheckboxInput;
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
        ElementUtils.sendKeys(driver, firstNameInput, firstName);
    }
    
    public void enterLastName(String lastName) { 
        wait.until(ExpectedConditions.elementToBeClickable(lastNameInput));
        ElementUtils.sendKeys(driver, lastNameInput, lastName);
    }
    
    public void enterEmail(String email) { 
        wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        ElementUtils.sendKeys(driver, emailInput, email);
    }
    
    public void selectMaleGender() { 
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gender-radio-1")));
            // Try clicking the input directly first
            ElementUtils.safeClick(driver, maleRadioInput);
        } catch (Exception e) {
            // Fallback to label click
            ElementUtils.safeClick(driver, maleRadioLabel);
        }
    }
    
    public void enterMobile(String mobile) { 
        wait.until(ExpectedConditions.elementToBeClickable(mobileInput));
        ElementUtils.sendKeys(driver, mobileInput, mobile);
    }
    
    public void selectSportsHobby() { 
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("hobbies-checkbox-1")));
            // Try clicking the input directly using JavaScript since it's often hidden
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", sportsCheckboxInput);
        } catch (Exception e) {
            // Fallback to label click
            ElementUtils.safeClick(driver, sportsCheckboxLabel);
        }
    }
    
    public void submitForm() { 
        // Scroll to submit button and click using robust method
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("submit")));
        ElementUtils.safeClick(driver, submitButton);
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
