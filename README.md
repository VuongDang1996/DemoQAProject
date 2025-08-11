# DemoQAProject

Selenium + TestNG Page Object Model tests for https://demoqa.com/.

## � Table of Contents
- [CI/CD Integration](#-cicd-integration)
  - [GitHub Actions Workflows](#github-actions-workflows)
  - [Manual Workflow Dispatch](#manual-workflow-dispatch)
  - [Viewing Test Results](#viewing-test-results)
- [Requirements](#requirements)
- [How to Run](#how-to-run)
- [Jenkins Integration](#jenkins-integration)
- [CI/CD Comparison](#cicd-comparison)
- [Configuration](#configuration)
- [Structure](#structure)
- [Workflow Status](#-workflow-status)

## �🚀 CI/CD Integration

This project features dual CI/CD integration with both **Jenkins** and **GitHub Actions** for maximum flexibility and coverage.

### GitHub Actions Workflows

The project includes comprehensive GitHub Actions workflows for automated testing and continuous integration:

#### 🔄 **Main CI/CD Pipeline** (`.github/workflows/ci-cd-pipeline.yml`)
- **Triggers**: Push to main/develop, Pull Requests, Daily scheduled runs, Manual dispatch
- **Multi-browser Testing**: Chrome, Firefox with parallel execution
- **Test Frameworks**: Both TestNG and Cucumber BDD support
- **Code Quality**: Automated code quality checks and security scanning
- **Reporting**: Automated test reports with GitHub Pages deployment
- **Artifact Management**: Test results and reports stored for 30 days

#### 🔍 **PR Validation** (`.github/workflows/pr-validation.yml`)
- **Fast Feedback**: Quick smoke tests for pull requests
- **Automated Comments**: PR status updates with test results
- **Quality Gates**: Prevents merging failing code

#### 🌙 **Nightly Testing** (`.github/workflows/nightly-tests.yml`)
- **Comprehensive Testing**: Full regression suite with extended timeout
- **Multi-environment**: Cross-browser compatibility testing
- **Failure Reporting**: Automatic issue creation for test failures
- **Screenshot Capture**: Visual evidence for debugging

#### 🚢 **Release Validation** (`.github/workflows/release-validation.yml`)
- **Production Testing**: Release-specific test validation
- **Multi-environment**: Staging and production environment testing
- **Extended Retention**: 90-day artifact storage for releases

#### 🔐 **Security Scanning** (`.github/workflows/security-scan.yml`)
- **Weekly Security Audits**: Automated dependency vulnerability scanning
- **OWASP Integration**: Security compliance reporting
- **License Compliance**: Open source license validation

### GitHub Actions Features

| Feature | Description | Status |
|---------|-------------|---------|
| **Parallel Execution** | Matrix strategy for browsers and test types | ✅ Active |
| **Multi-browser Support** | Chrome, Firefox, Edge testing | ✅ Active |
| **Artifact Management** | Automated test report storage | ✅ Active |
| **GitHub Pages** | Automated report deployment | ✅ Active |
| **PR Integration** | Automated PR validation and commenting | ✅ Active |
| **Security Scanning** | OWASP and dependency checks | ✅ Active |
| **Failure Reporting** | Automatic issue creation for failures | ✅ Active |

### Manual Workflow Dispatch

You can manually trigger workflows with custom parameters:

1. Go to **Actions** tab in GitHub repository
2. Select **DemoQA Test Automation Pipeline**
3. Click **Run workflow**
4. Choose parameters:
   - **Test Suite**: full, smoke, regression, testng, cucumber
   - **Browser**: chrome, firefox
   - **Environment**: staging, production

### Viewing Test Results

- **Live Results**: Check the Actions tab for real-time execution
- **Test Reports**: Available in run artifacts and GitHub Pages
- **PR Comments**: Automated status updates on pull requests
- **GitHub Pages**: https://vuongdang1996.github.io/DemoQAProject/test-reports/

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

## CI/CD Comparison

| Feature | GitHub Actions | Jenkins |
|---------|----------------|---------|
| **Setup Complexity** | ✅ Zero setup (cloud-native) | ⚠️ Requires infrastructure |
| **Cost** | ✅ Free for public repos | ⚠️ Infrastructure costs |
| **Matrix Testing** | ✅ Native matrix support | ⚠️ Requires pipeline scripting |
| **Integration** | ✅ Deep GitHub integration | ⚠️ Webhook-based |
| **Customization** | ⚠️ Limited plugin ecosystem | ✅ Extensive plugin library |
| **Enterprise Features** | ⚠️ Limited enterprise features | ✅ Advanced enterprise support |

Choose **GitHub Actions** for:
- Public repositories
- Simple CI/CD needs
- Cloud-native approach
- Rapid setup

Choose **Jenkins** for:
- Enterprise environments
- Complex deployment pipelines
- On-premise requirements
- Advanced plugin needs
- You can also override via system properties, e.g. `-Dbrowser=chrome`.
- Headless mode: set `-DHEADLESS=true`.

### Parallel & Reports
- **TestNG**: Tests run in parallel per `testng.xml` (suite has `parallel="tests"` and `thread-count="3"`).
- **Cucumber**: Scenarios run in parallel using TestNG DataProvider and Cucumber parallel execution.
- **TestNG Reports**: ExtentReports are generated under `target/reports/ExtentReport_<timestamp>.html`.
- **Cucumber Reports**: HTML reports generated under `target/cucumber-reports/`.
- **GitHub Actions Reports**: Automated test reports deployed to GitHub Pages
- **Jenkins Reports**: HTML Publisher plugin for report visualization
- On failure, screenshots are saved and attached to reports.

### Environment Variables
- `HEADLESS=true` - Enable headless browser mode (required for CI/CD)
- `BROWSER=chrome|firefox` - Override browser selection
- `BASE_URL=https://demoqa.com` - Override base URL for testing

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
- src/test/java/com/demoqa/listeners/TestListener.java – Extent + screenshots on failure
- src/test/java/com/demoqa/utils/ExtentManager.java – Extent singleton

### GitHub Actions Configuration
- `.github/workflows/ci-cd-pipeline.yml` – Main CI/CD pipeline with matrix testing
- `.github/workflows/pr-validation.yml` – Pull request validation and commenting
- `.github/workflows/nightly-tests.yml` – Comprehensive nightly regression testing
- `.github/workflows/release-validation.yml` – Release-specific validation testing
- `.github/workflows/security-scan.yml` – Security and dependency scanning

### Jenkins Configuration
- `Jenkinsfile` – Pipeline as Code configuration
- `jenkins/` – Jenkins setup files and documentation

## Notes
- WebDriverManager downloads the matching ChromeDriver automatically.
- If running headless (CI), set the environment variable `HEADLESS=true` and adjust options if desired.
- GitHub Actions workflows automatically run in headless mode with virtual display (Xvfb).
- All workflows include proper browser setup and dependency caching for optimal performance.
- Test artifacts and reports are automatically stored and accessible via GitHub Actions interface.

## 📊 **Workflow Status**

[![CI/CD Pipeline](https://github.com/VuongDang1996/DemoQAProject/actions/workflows/ci-cd-pipeline.yml/badge.svg)](https://github.com/VuongDang1996/DemoQAProject/actions/workflows/ci-cd-pipeline.yml)
[![PR Validation](https://github.com/VuongDang1996/DemoQAProject/actions/workflows/pr-validation.yml/badge.svg)](https://github.com/VuongDang1996/DemoQAProject/actions/workflows/pr-validation.yml)
[![Nightly Tests](https://github.com/VuongDang1996/DemoQAProject/actions/workflows/nightly-tests.yml/badge.svg)](https://github.com/VuongDang1996/DemoQAProject/actions/workflows/nightly-tests.yml)
[![Security Scan](https://github.com/VuongDang1996/DemoQAProject/actions/workflows/security-scan.yml/badge.svg)](https://github.com/VuongDang1996/DemoQAProject/actions/workflows/security-scan.yml)
