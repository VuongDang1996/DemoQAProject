package com.demoqa.cucumber.stepdefinitions;

import com.demoqa.cucumber.CucumberBaseTest;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;

public class Hooks {

    @Before
    public void setUp() throws IOException {
        CucumberBaseTest.initializeDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take screenshot on failure
            final byte[] screenshot = ((TakesScreenshot) CucumberBaseTest.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }
        CucumberBaseTest.quitDriver();
    }

    @BeforeStep
    public void beforeStep() {
        // Add any step-level setup if needed
    }

    @AfterStep
    public void afterStep() {
        // Add any step-level cleanup if needed
    }
}
