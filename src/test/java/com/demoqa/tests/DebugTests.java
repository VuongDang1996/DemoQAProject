package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class DebugTests extends BaseTest {

    @Test
    public void debugProgressBarElements() {
        getDriver().get("https://demoqa.com/progress-bar");
        
        System.out.println("=== DEBUG: Progress Bar Page Elements ===");
        
        // Find start button by different methods
        try {
            WebElement startBtn = getDriver().findElement(By.id("startStopButton"));
            System.out.println("✅ Found start button by ID: " + startBtn.getTagName());
            System.out.println("Button text: '" + startBtn.getText() + "'");
            System.out.println("Button classes: " + startBtn.getAttribute("class"));
        } catch (Exception e) {
            System.out.println("❌ Could not find start button by ID: " + e.getMessage());
        }
        
        // Try to find button by other methods
        try {
            List<WebElement> buttons = getDriver().findElements(By.tagName("button"));
            System.out.println("Found " + buttons.size() + " button elements:");
            for (int i = 0; i < buttons.size(); i++) {
                WebElement btn = buttons.get(i);
                System.out.println("  Button " + i + ": id='" + btn.getAttribute("id") + "', text='" + btn.getText() + "', class='" + btn.getAttribute("class") + "'");
            }
        } catch (Exception e) {
            System.out.println("❌ Could not find buttons: " + e.getMessage());
        }
        
        // Find progress bar
        try {
            WebElement progressBar = getDriver().findElement(By.id("progressBar"));
            System.out.println("✅ Found progress bar by ID: " + progressBar.getTagName());
            System.out.println("Progress bar classes: " + progressBar.getAttribute("class"));
            System.out.println("Progress bar aria-valuenow: " + progressBar.getAttribute("aria-valuenow"));
            System.out.println("Progress bar style: " + progressBar.getAttribute("style"));
        } catch (Exception e) {
            System.out.println("❌ Could not find progress bar by ID: " + e.getMessage());
        }
        
        // Try to find progress elements by other methods
        try {
            List<WebElement> progressElements = getDriver().findElements(By.className("progress-bar"));
            System.out.println("Found " + progressElements.size() + " progress-bar elements:");
            for (int i = 0; i < progressElements.size(); i++) {
                WebElement elem = progressElements.get(i);
                System.out.println("  Progress " + i + ": id='" + elem.getAttribute("id") + "', class='" + elem.getAttribute("class") + "'");
            }
        } catch (Exception e) {
            System.out.println("❌ Could not find progress elements: " + e.getMessage());
        }
    }

    @Test
    public void debugPracticeFormElements() {
        getDriver().get("https://demoqa.com/automation-practice-form");
        
        System.out.println("=== DEBUG: Practice Form Page Elements ===");
        
        // Check sports hobby checkbox
        try {
            WebElement sportsLabel = getDriver().findElement(By.xpath("//label[@for='hobbies-checkbox-1']"));
            System.out.println("✅ Found sports hobby label by xpath");
            System.out.println("Sports label text: '" + sportsLabel.getText() + "'");
            System.out.println("Sports label classes: " + sportsLabel.getAttribute("class"));
        } catch (Exception e) {
            System.out.println("❌ Could not find sports hobby label: " + e.getMessage());
        }
        
        // Try to find hobby elements by other methods
        try {
            List<WebElement> hobbyLabels = getDriver().findElements(By.xpath("//label[contains(@for,'hobbies-checkbox')]"));
            System.out.println("Found " + hobbyLabels.size() + " hobby checkbox labels:");
            for (int i = 0; i < hobbyLabels.size(); i++) {
                WebElement label = hobbyLabels.get(i);
                System.out.println("  Hobby " + i + ": for='" + label.getAttribute("for") + "', text='" + label.getText() + "'");
            }
        } catch (Exception e) {
            System.out.println("❌ Could not find hobby labels: " + e.getMessage());
        }
        
        // Check submit button
        try {
            WebElement submitBtn = getDriver().findElement(By.id("submit"));
            System.out.println("✅ Found submit button by ID");
            System.out.println("Submit button text: '" + submitBtn.getText() + "'");
            System.out.println("Submit button classes: " + submitBtn.getAttribute("class"));
        } catch (Exception e) {
            System.out.println("❌ Could not find submit button: " + e.getMessage());
        }
    }
}
