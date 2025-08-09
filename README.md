# DemoQAProject

Selenium + TestNG Page Object Model tests for https://demoqa.com/.

## Requirements
- Java 11+
- Maven 3.8+
- Google Chrome installed

## How to run

### TestNG Framework
1. Ensure Java and Maven are on your PATH.
2. Execute the full TestNG suite:

```
mvn -q -DskipTests=false test
```

This uses Surefire to run `src/test/resources/testng.xml`.

### Cucumber Framework
1. Run all Cucumber tests:
```
mvn test -Pcucumber
```

2. Run only Smoke tests:
```
mvn test -Pcucumber-smoke
```

3. Run specific runner:
```
mvn test -Dtest=SmokeTestRunner
mvn test -Dtest=RegressionTestRunner
mvn test -Dtest=AllTestsRunner
```

4. Run with TestNG XML:
```
mvn test -DsuiteXmlFile=src/test/resources/cucumber-testng.xml
```

### Configuration
- Edit `src/test/resources/config.properties` to change `browser` (chrome|firefox) and `baseUrl`.

## Jenkins Integration

This project includes complete Jenkins CI/CD integration with pipeline and freestyle job configurations.

### Quick Setup
1. Install required Jenkins plugins (Git, Maven, HTML Publisher, JUnit, Pipeline)
2. Configure tools (JDK-11, Maven-3.9.0) in Global Tool Configuration
3. Create pipeline job using the provided `Jenkinsfile`
4. Set up GitHub webhook for automatic builds

### Available Jenkins Profiles
- `jenkins` - Headless mode optimized for CI/CD
- Combined with test profiles: `-Pjenkins,cucumber-smoke`

### Jenkins Files
- `Jenkinsfile` - Pipeline as Code configuration
- `jenkins/README.md` - Detailed setup instructions
- `jenkins/job-config.xml` - Freestyle job configuration
- `jenkins/Dockerfile` - Docker agent setup

For detailed Jenkins setup instructions, see [jenkins/README.md](jenkins/README.md).
- You can also override via system properties, e.g. `-Dbrowser=chrome`.
- Headless mode: set `-DHEADLESS=true`.

### Parallel & Reports
- **TestNG**: Tests run in parallel per `testng.xml` (suite has `parallel="tests"` and `thread-count="3"`).
- **Cucumber**: Scenarios run in parallel using TestNG DataProvider and Cucumber parallel execution.
- **TestNG Reports**: ExtentReports are generated under `target/reports/ExtentReport_<timestamp>.html`.
- **Cucumber Reports**: HTML reports generated under `target/cucumber-reports/`.
- On failure, screenshots are saved and attached to reports.

## Structure

### TestNG Framework
- src/test/java/com/demoqa/base/BaseTest.java – WebDriver setup/teardown
- src/test/java/com/demoqa/pages – Page Objects
- src/test/java/com/demoqa/tests – TestNG tests
- src/test/resources/testng.xml – Test suite

### Cucumber Framework
- src/test/java/com/demoqa/cucumber/CucumberBaseTest.java – Cucumber WebDriver setup
- src/test/java/com/demoqa/cucumber/stepdefinitions – Step definitions
- src/test/java/com/demoqa/cucumber/runners – Test runners
- src/test/resources/features – Feature files (Gherkin)
- src/test/resources/cucumber-testng.xml – Cucumber TestNG suite

### Shared Components
- src/test/java/com/demoqa/pages – Page Objects (shared between frameworks)
- src/test/java/com/demoqa/utils – Utility classes
- src/test/resources/config.properties – Configuration
 - src/test/resources/config.properties – Browser + base URL
 - src/test/java/com/demoqa/listeners/TestListener.java – Extent + screenshots on failure
 - src/test/java/com/demoqa/utils/ExtentManager.java – Extent singleton

## Notes
- WebDriverManager downloads the matching ChromeDriver automatically.
- If running headless (CI), set the environment variable `HEADLESS=true` and adjust options if desired.
