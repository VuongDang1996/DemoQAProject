package com.demoqa.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.demoqa.cucumber.stepdefinitions"},
        tags = "@Regression",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/regression-html-report",
                "json:target/cucumber-reports/regression.json",
                "junit:target/cucumber-reports/regression.xml"
        },
        monochrome = true,
        publish = true
)
public class RegressionTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
