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
    
    public static void waitForElement(WebDriver driver, WebElement element, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    public static void dragAndDropWithRetry(WebDriver driver, WebElement source, WebElement target) {
        int attempts = 0;
        int maxAttempts = 3;
        
        while (attempts < maxAttempts) {
            try {
                scrollToElement(driver, source);
                
                // Firefox-specific drag and drop with more explicit actions
                Actions actions = new Actions(driver);
                actions.clickAndHold(source)
                       .pause(Duration.ofMillis(500))
                       .moveToElement(target)
                       .pause(Duration.ofMillis(500))
                       .release()
                       .perform();
                       
                // Wait a bit for the action to complete
                Thread.sleep(1000);
                break;
                
            } catch (Exception e) {
                attempts++;
                System.out.println("Drag and drop attempt " + attempts + " failed: " + e.getMessage());
                
                if (attempts == maxAttempts) {
                    // Final fallback to JavaScript
                    System.out.println("Using JavaScript fallback for drag and drop");
                    ((JavascriptExecutor) driver).executeScript(
                        "function simulateDragDrop(sourceNode, destinationNode) {" +
                        "  var EVENT_TYPES = {" +
                        "    DRAG_END: 'dragend'," +
                        "    DRAG_START: 'dragstart'," +
                        "    DROP: 'drop'" +
                        "  };" +
                        "  function createCustomEvent(type) {" +
                        "    var event = new CustomEvent('CustomEvent');" +
                        "    event.initCustomEvent(type, true, true, null);" +
                        "    event.dataTransfer = {" +
                        "      data: {}," +
                        "      setData: function(type, val) { this.data[type] = val; }," +
                        "      getData: function(type) { return this.data[type]; }" +
                        "    };" +
                        "    return event;" +
                        "  }" +
                        "  function dispatchEvent(node, type, event) {" +
                        "    if (node.dispatchEvent) { node.dispatchEvent(event); }" +
                        "  }" +
                        "  var event = createCustomEvent(EVENT_TYPES.DRAG_START);" +
                        "  dispatchEvent(sourceNode, EVENT_TYPES.DRAG_START, event);" +
                        "  var dropEvent = createCustomEvent(EVENT_TYPES.DROP);" +
                        "  dropEvent.dataTransfer = event.dataTransfer;" +
                        "  dispatchEvent(destinationNode, EVENT_TYPES.DROP, dropEvent);" +
                        "  var dragEndEvent = createCustomEvent(EVENT_TYPES.DRAG_END);" +
                        "  dragEndEvent.dataTransfer = event.dataTransfer;" +
                        "  dispatchEvent(sourceNode, EVENT_TYPES.DRAG_END, dragEndEvent);" +
                        "}" +
                        "simulateDragDrop(arguments[0], arguments[1]);", source, target);
                }
                
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
    
    public static void waitForTextChange(WebDriver driver, WebElement element, String originalText, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        wait.until(driver1 -> !element.getText().equals(originalText));
    }
}
