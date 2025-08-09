package com.demoqa.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ElementUtils {
    
    public static void safeClick(WebDriver driver, WebElement element) {
        try {
            // First try scrolling and regular click
            scrollToElement(driver, element);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            // If regular click fails, use JavaScript click
            System.out.println("Regular click failed, using JavaScript click: " + e.getMessage());
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
    
    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
        try {
            Thread.sleep(1000); // Wait for scroll animation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public static void moveToElement(WebDriver driver, WebElement element) {
        try {
            scrollToElement(driver, element);
            new Actions(driver).moveToElement(element).perform();
        } catch (Exception e) {
            System.out.println("Move to element failed: " + e.getMessage());
            // Fallback to JavaScript hover
            ((JavascriptExecutor) driver).executeScript(
                "var event = new MouseEvent('mouseover', {bubbles: true}); arguments[0].dispatchEvent(event);", element);
        }
    }
    
    public static void rightClick(WebDriver driver, WebElement element) {
        try {
            scrollToElement(driver, element);
            new Actions(driver).contextClick(element).perform();
        } catch (Exception e) {
            System.out.println("Right click failed, using JavaScript: " + e.getMessage());
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new MouseEvent('contextmenu', {bubbles: true}));", element);
        }
    }
    
    public static void doubleClick(WebDriver driver, WebElement element) {
        try {
            scrollToElement(driver, element);
            new Actions(driver).doubleClick(element).perform();
        } catch (Exception e) {
            System.out.println("Double click failed, using JavaScript: " + e.getMessage());
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new MouseEvent('dblclick', {bubbles: true}));", element);
        }
    }
    
    public static void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
        try {
            scrollToElement(driver, source);
            new Actions(driver).dragAndDrop(source, target).perform();
        } catch (Exception e) {
            System.out.println("Drag and drop failed, using JavaScript: " + e.getMessage());
            ((JavascriptExecutor) driver).executeScript(
                "function dragAndDrop(source, target) {" +
                "  var dataTransfer = new DataTransfer();" +
                "  source.dispatchEvent(new DragEvent('dragstart', {dataTransfer: dataTransfer}));" +
                "  target.dispatchEvent(new DragEvent('drop', {dataTransfer: dataTransfer}));" +
                "}" +
                "dragAndDrop(arguments[0], arguments[1]);", source, target);
        }
    }
    
    public static void sendKeys(WebDriver driver, WebElement element, String text) {
        try {
            scrollToElement(driver, element);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            System.out.println("SendKeys failed, using JavaScript: " + e.getMessage());
            ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));", element, text);
        }
    }
    
    public static void waitForElementAndClick(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        safeClick(driver, element);
    }
}
