package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class PracticeFormDebugTests extends BaseTest {

    @Test
    public void debugPracticeFormElements() {
        getDriver().get("https://demoqa.com/automation-practice-form");
        
        System.out.println("=== DEBUGGING PRACTICE FORM ELEMENTS ===");
        
        // Check gender radio buttons
        System.out.println("\n--- GENDER RADIO BUTTONS ---");
        try {
            List<WebElement> genderRadios = getDriver().findElements(By.xpath("//input[@name='gender']"));
            for (int i = 0; i < genderRadios.size(); i++) {
                WebElement radio = genderRadios.get(i);
                String id = radio.getAttribute("id");
                String value = radio.getAttribute("value");
                System.out.println("Gender Radio " + i + ": id=" + id + ", value=" + value);
                
                // Find corresponding label
                try {
                    WebElement label = getDriver().findElement(By.xpath("//label[@for='" + id + "']"));
                    System.out.println("  Label text: " + label.getText());
                } catch (Exception e) {
                    System.out.println("  No label found for: " + id);
                }
            }
        } catch (Exception e) {
            System.out.println("Error finding gender radios: " + e.getMessage());
        }
        
        // Check hobbies checkboxes
        System.out.println("\n--- HOBBIES CHECKBOXES ---");
        try {
            List<WebElement> hobbiesCheckboxes = getDriver().findElements(By.xpath("//input[@type='checkbox']"));
            for (int i = 0; i < hobbiesCheckboxes.size(); i++) {
                WebElement checkbox = hobbiesCheckboxes.get(i);
                String id = checkbox.getAttribute("id");
                String value = checkbox.getAttribute("value");
                System.out.println("Hobby Checkbox " + i + ": id=" + id + ", value=" + value);
                
                // Find corresponding label
                try {
                    WebElement label = getDriver().findElement(By.xpath("//label[@for='" + id + "']"));
                    System.out.println("  Label text: " + label.getText());
                } catch (Exception e) {
                    System.out.println("  No label found for: " + id);
                }
            }
        } catch (Exception e) {
            System.out.println("Error finding hobby checkboxes: " + e.getMessage());
        }
        
        // Check submit button
        System.out.println("\n--- SUBMIT BUTTON ---");
        try {
            WebElement submitButton = getDriver().findElement(By.id("submit"));
            System.out.println("Submit button found: " + submitButton.getText());
            System.out.println("Submit button class: " + submitButton.getAttribute("class"));
        } catch (Exception e) {
            System.out.println("Submit button not found with id='submit': " + e.getMessage());
            
            // Try alternative selectors
            try {
                WebElement altSubmit = getDriver().findElement(By.xpath("//button[contains(text(),'Submit')]"));
                System.out.println("Alternative submit button found: " + altSubmit.getText());
                System.out.println("Alternative submit button id: " + altSubmit.getAttribute("id"));
            } catch (Exception ex) {
                System.out.println("No submit button found");
            }
        }
        
        System.out.println("\n=== DEBUG COMPLETE ===");
    }
    
    @Test
    public void testFormSubmission() {
        getDriver().get("https://demoqa.com/automation-practice-form");
        
        System.out.println("=== TESTING FORM SUBMISSION ===");
        
        try {
            // Fill basic fields
            getDriver().findElement(By.id("firstName")).sendKeys("John");
            getDriver().findElement(By.id("lastName")).sendKeys("Doe");
            getDriver().findElement(By.id("userEmail")).sendKeys("john.doe@example.com");
            getDriver().findElement(By.id("userNumber")).sendKeys("1234567890");
            
            // Try clicking male gender
            WebElement maleLabel = getDriver().findElement(By.xpath("//label[@for='gender-radio-1']"));
            System.out.println("Male label text: " + maleLabel.getText());
            maleLabel.click();
            
            // Try clicking sports hobby
            WebElement sportsLabel = getDriver().findElement(By.xpath("//label[@for='hobbies-checkbox-1']"));
            System.out.println("Sports label text: " + sportsLabel.getText());
            sportsLabel.click();
            
            // Try submit
            WebElement submitBtn = getDriver().findElement(By.id("submit"));
            submitBtn.click();
            
            // Wait and check for modal
            Thread.sleep(2000);
            
            System.out.println("\n--- CHECKING FOR MODAL ---");
            try {
                WebElement modal = getDriver().findElement(By.id("example-modal-sizes-title-lg"));
                System.out.println("Modal found with title: " + modal.getText());
            } catch (Exception e) {
                System.out.println("Modal with id='example-modal-sizes-title-lg' not found");
                
                // Try other modal selectors
                try {
                    WebElement altModal = getDriver().findElement(By.className("modal-title"));
                    System.out.println("Alternative modal found: " + altModal.getText());
                } catch (Exception ex) {
                    System.out.println("No modal found at all");
                    
                    // Check if form was submitted by looking for any modal
                    List<WebElement> modals = getDriver().findElements(By.xpath("//div[contains(@class,'modal')]"));
                    System.out.println("Total modals found: " + modals.size());
                }
            }
            
        } catch (Exception e) {
            System.out.println("Error during form submission: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n=== FORM SUBMISSION TEST COMPLETE ===");
    }
}
