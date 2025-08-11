package com.demoqa.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.demoqa.cucumber.stepdefinitions"},
        tags = "@Smoke",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/smoke-html-report",
                "json:target/cucumber-reports/smoke.json",
                "junit:target/cucumber-reports/smoke.xml",
                "rerun:target/cucumber-reports/smoke-rerun.txt"
        },
        monochrome = true,
        publish = true
)
public class SmokeTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
