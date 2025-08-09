package com.demoqa.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.demoqa.cucumber.stepdefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/all-tests-html-report",
                "json:target/cucumber-reports/all-tests.json",
                "junit:target/cucumber-reports/all-tests.xml",
                "timeline:target/cucumber-reports/timeline"
        },
        monochrome = true,
        publish = true
)
public class AllTestsRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
