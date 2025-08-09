# DemoQAProject

Selenium + TestNG Page Object Model tests for https://demoqa.com/.

## Requirements
- Java 11+
- Maven 3.8+
- Google Chrome installed

## How to run
1. Ensure Java and Maven are on your PATH.
2. Execute the full suite:

```
mvn -q -DskipTests=false test
```

This uses Surefire to run `src/test/resources/testng.xml`.

### Configuration
- Edit `src/test/resources/config.properties` to change `browser` (chrome|firefox) and `baseUrl`.
- You can also override via system properties, e.g. `-Dbrowser=chrome`.
- Headless mode: set `-DHEADLESS=true`.

### Parallel & Reports
- Tests run in parallel per `testng.xml` (suite has `parallel="tests"` and `thread-count="3"`).
- ExtentReports are generated under `target/reports/ExtentReport_<timestamp>.html`.
- On failure, screenshots are saved in `target/screenshots` and attached to the report.

## Structure
- src/test/java/com/demoqa/base/BaseTest.java – WebDriver setup/teardown
- src/test/java/com/demoqa/pages – Page Objects
- src/test/java/com/demoqa/tests – TestNG tests
- src/test/resources/testng.xml – Test suite
 - src/test/resources/config.properties – Browser + base URL
 - src/test/java/com/demoqa/listeners/TestListener.java – Extent + screenshots on failure
 - src/test/java/com/demoqa/utils/ExtentManager.java – Extent singleton

## Notes
- WebDriverManager downloads the matching ChromeDriver automatically.
- If running headless (CI), set the environment variable `HEADLESS=true` and adjust options if desired.
