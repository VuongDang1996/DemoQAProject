package com.demoqa.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    private static final ThreadLocal<WebDriver> driverTL = new ThreadLocal<>();
    protected Properties config;

    protected WebDriver getDriver() {
        return driverTL.get();
    }

    private void setDriver(WebDriver driver) {
        driverTL.set(driver);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        // Load configuration
        config = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties")) {
            config.load(fis);
        }

        String browser = System.getProperty("browser", config.getProperty("browser", "chrome")).toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getProperty("HEADLESS", "false"));

        WebDriver localDriver;
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ff = new FirefoxOptions();
                if (headless) ff.addArguments("-headless");
                localDriver = new FirefoxDriver(ff);
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions co = new ChromeOptions();
                co.addArguments("--start-maximized");
                co.addArguments("--disable-blink-features=AutomationControlled");
                co.addArguments("--disable-extensions");
                co.addArguments("--no-sandbox");
                co.addArguments("--disable-dev-shm-usage");
                co.addArguments("--disable-gpu");
                co.addArguments("--remote-allow-origins=*");
                // Block ads and pop-ups
                co.addArguments("--disable-popup-blocking");
                co.addArguments("--disable-default-apps");
                co.addArguments("--disable-background-networking");
                if (headless) co.addArguments("--headless=new");
                localDriver = new ChromeDriver(co);
                break;
        }

        setDriver(localDriver);
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver d = getDriver();
        if (d != null) {
            d.quit();
            driverTL.remove();
        }
    }
}
