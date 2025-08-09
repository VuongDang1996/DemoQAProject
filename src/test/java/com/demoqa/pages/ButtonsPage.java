package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;

public class ButtonsPage {
    private final WebDriver driver;

    @FindBy(id = "doubleClickBtn") private WebElement doubleClickButton;
    @FindBy(id = "rightClickBtn") private WebElement rightClickButton;
    @FindBy(xpath = "//button[text()='Click Me']") private WebElement clickMeButton;
    @FindBy(xpath = "//*[@id='doubleClickMessage' or contains(text(),'double click')]") private WebElement doubleClickMessage;
    @FindBy(xpath = "//*[@id='rightClickMessage' or contains(text(),'right click')]") private WebElement rightClickMessage;
    @FindBy(xpath = "//*[@id='dynamicClickMessage' or contains(text(),'dynamic click')]") private WebElement dynamicClickMessage;

    public ButtonsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void doubleClickButton() {
        scrollToElement(doubleClickButton);
        // Use JavaScript to trigger double click event
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new MouseEvent('dblclick', {bubbles: true}));", doubleClickButton);
    }

    public void rightClickButton() {
        scrollToElement(rightClickButton);
        // Use JavaScript to trigger context click event
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new MouseEvent('contextmenu', {bubbles: true}));", rightClickButton);
    }

    public void clickDynamicButton() {
        try {
            // Find all buttons with "Click Me" text
            List<WebElement> clickMeButtons = driver.findElements(By.xpath("//button[text()='Click Me']"));
            
            // The dynamic click button is typically the third one (index 2)
            if (clickMeButtons.size() >= 3) {
                WebElement dynamicButton = clickMeButtons.get(2);
                scrollToElement(dynamicButton);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dynamicButton);
            } else if (!clickMeButtons.isEmpty()) {
                // Fallback to the last one found
                WebElement dynamicButton = clickMeButtons.get(clickMeButtons.size() - 1);
                scrollToElement(dynamicButton);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dynamicButton);
            }
        } catch (Exception e) {
            System.out.println("Error clicking dynamic button: " + e.getMessage());
        }
    }

    public String getDoubleClickMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(doubleClickMessage));
        return doubleClickMessage.getText();
    }

    public String getRightClickMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(rightClickMessage));
        return rightClickMessage.getText();
    }

    public String getDynamicClickMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(dynamicClickMessage));
        return dynamicClickMessage.getText();
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        try {
            Thread.sleep(500); // Brief pause for scroll animation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
