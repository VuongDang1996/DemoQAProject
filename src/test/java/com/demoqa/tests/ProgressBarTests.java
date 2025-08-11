package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.ProgressBarPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProgressBarTests extends BaseTest {

    @Test
    public void progressBarTest() {
        System.out.println("Starting Progress Bar Test...");
        getDriver().get("https://demoqa.com/progress-bar");
        ProgressBarPage progressBarPage = new ProgressBarPage(getDriver());
        
        // Wait for page to load and get initial state
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Initial button text: " + progressBarPage.getButtonText());
        System.out.println("Initial progress value: " + progressBarPage.getProgressValue());
        
        // Click start button
        System.out.println("Clicking start button...");
        progressBarPage.clickStartButton();
        
        // Wait for progress bar to reach 100% with increased timeout and better monitoring
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(60));
        boolean progressCompleted = wait.until(driver -> {
            try {
                String progressValue = progressBarPage.getProgressValue();
                String buttonText = progressBarPage.getButtonText();
                boolean isComplete = progressBarPage.isProgressComplete();
                
                System.out.println("Current progress: " + progressValue + "%, Button: '" + buttonText + "', Complete: " + isComplete);
                
                // Check multiple completion indicators
                return ("100".equals(progressValue) || 
                        "Reset".equalsIgnoreCase(buttonText) || 
                        isComplete);
            } catch (Exception e) {
                System.out.println("Error checking progress: " + e.getMessage());
                return false;
            }
        });
        
        // Verify progress completed
        Assert.assertTrue(progressCompleted, "Progress bar should complete within 60 seconds");
        
        // Double-check final state
        String finalProgress = progressBarPage.getProgressValue();
        String finalButtonText = progressBarPage.getButtonText();
        boolean finalComplete = progressBarPage.isProgressComplete();
        
        System.out.println("Final state - Progress: " + finalProgress + "%, Button: '" + finalButtonText + "', Complete: " + finalComplete);
        
        // Accept any of the completion indicators
        Assert.assertTrue(
            "100".equals(finalProgress) || 
            "Reset".equalsIgnoreCase(finalButtonText) || 
            finalComplete,
            "Progress bar should show completion. Progress: " + finalProgress + "%, Button: '" + finalButtonText + "', Complete: " + finalComplete
        );
        
        System.out.println("Progress Bar Test completed successfully!");
    }
}
