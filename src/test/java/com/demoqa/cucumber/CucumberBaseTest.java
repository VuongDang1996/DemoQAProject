package com.demoqa.cucumber;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class CucumberBaseTest {
    private static final ThreadLocal<WebDriver> driverTL = new ThreadLocal<>();
    protected static Properties config;

    public static WebDriver getDriver() {
        return driverTL.get();
    }

    private static void setDriver(WebDriver driver) {
        driverTL.set(driver);
    }

    public static void initializeDriver() throws IOException {
        // Load configuration
        config = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties")) {
            config.load(fis);
        }

        String browser = System.getProperty("browser", config.getProperty("browser", "chrome"));
        boolean headless = Boolean.parseBoolean(System.getProperty("HEADLESS", "false"));

        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-blink-features=AutomationControlled");
                chromeOptions.addArguments("--disable-extensions");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-web-security");
                chromeOptions.addArguments("--allow-running-insecure-content");
                
                // Enhanced Ad Blocking for Cucumber
                chromeOptions.addArguments("--disable-popup-blocking");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-default-apps");
                chromeOptions.addArguments("--disable-background-timer-throttling");
                chromeOptions.addArguments("--disable-renderer-backgrounding");
                chromeOptions.addArguments("--disable-backgrounding-occluded-windows");
                chromeOptions.addArguments("--disable-component-extensions-with-background-pages");
                chromeOptions.addArguments("--block-new-web-contents");
                
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        
        setDriver(driver);
    }

    public static void quitDriver() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            driverTL.remove();
        }
    }
}
