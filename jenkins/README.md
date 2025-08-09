# Jenkins Integration Guide

This guide explains how to integrate Jenkins with the DemoQA Test Automation Framework.

## Overview

The DemoQA project supports both TestNG and Cucumber frameworks and can be easily integrated with Jenkins for continuous integration and automated testing.

## Jenkins Configuration Files

- `Jenkinsfile` - Pipeline as Code configuration
- `jenkins/job-config.xml` - Freestyle job configuration
- `jenkins/Dockerfile` - Docker agent with all required tools
- `jenkins/setup.sh` - Setup script with instructions

## Prerequisites

### Jenkins Server Requirements
- Jenkins 2.400+ 
- Java 11+
- Maven 3.8+
- Git
- Chrome browser (for headless testing)

### Required Jenkins Plugins
- Git plugin
- Maven Integration plugin
- HTML Publisher plugin
- JUnit plugin
- Pipeline plugin
- GitHub plugin
- Build Timeout plugin
- Workspace Cleanup plugin

## Setup Instructions

### 1. Install Jenkins Plugins

Go to **Jenkins > Manage Jenkins > Manage Plugins** and install:
- Git plugin
- Maven Integration plugin  
- HTML Publisher plugin
- JUnit plugin
- Pipeline plugin
- GitHub plugin

### 2. Configure Global Tools

Go to **Jenkins > Manage Jenkins > Global Tool Configuration**:

**JDK Configuration:**
- Name: `JDK-11`
- JAVA_HOME: `/usr/lib/jvm/java-11-openjdk-amd64`

**Maven Configuration:**
- Name: `Maven-3.9.0` 
- MAVEN_HOME: `/opt/maven`

**Git Configuration:**
- Name: `Default`
- Path to Git executable: `git`

### 3. Create Pipeline Job (Recommended)

1. Click **New Item**
2. Enter name: `DemoQA-Pipeline`
3. Select **Pipeline** and click OK
4. In Pipeline section:
   - Definition: **Pipeline script from SCM**
   - SCM: **Git**
   - Repository URL: `https://github.com/VuongDang1996/DemoQAProject.git`
   - Script Path: `Jenkinsfile`
5. Save the job

### 4. Alternative: Freestyle Job

1. Click **New Item**
2. Enter name: `DemoQA-Freestyle`
3. Select **Freestyle project**
4. Import configuration from `jenkins/job-config.xml`

## Test Execution Options

The pipeline supports multiple test execution modes via parameters:

### Test Suite Options
- `all` - Run all test suites
- `testng` - Run TestNG tests only
- `cucumber` - Run all Cucumber tests
- `cucumber-smoke` - Run Cucumber smoke tests only
- `cucumber-regression` - Run Cucumber regression tests only

### Execution Modes
- **Headless Mode**: Run tests without GUI (default: true)
- **Regular Mode**: Run tests with browser GUI

## Maven Profiles

The project includes several Maven profiles for different execution scenarios:

```bash
# TestNG tests
mvn test -Ptestng

# All Cucumber tests  
mvn test -Pcucumber

# Cucumber smoke tests
mvn test -Pcucumber-smoke

# Cucumber regression tests
mvn test -Pcucumber-regression

# Jenkins profile (headless mode)
mvn test -Pjenkins
```

## Docker Agent Setup

For containerized execution, use the provided Dockerfile:

```bash
# Build Docker image
cd jenkins/
docker build -t demoqa-jenkins-agent .

# Run container
docker run -d --name demoqa-agent demoqa-jenkins-agent
```

## GitHub Integration

### Webhook Setup
1. Go to GitHub repository Settings > Webhooks
2. Add webhook:
   - Payload URL: `http://your-jenkins-url/github-webhook/`
   - Content type: `application/json`
   - Events: Push events
3. Jenkins will automatically trigger builds on code push

### Branch Strategy
- **main** branch triggers full test suite
- **feature/** branches trigger smoke tests only
- **release/** branches trigger regression tests

## Reports and Artifacts

### Generated Reports
- **ExtentReports**: `target/reports/*.html`
- **Cucumber HTML**: `target/cucumber-reports/*.html`
- **TestNG Reports**: `target/surefire-reports/`
- **JUnit XML**: `target/surefire-reports/TEST-*.xml`

### Jenkins Report Integration
- JUnit results are published automatically
- HTML reports are available via Jenkins UI
- Screenshots are captured on test failures
- Build artifacts are archived for 30 days

## Pipeline Stages

The Jenkins pipeline includes the following stages:

1. **Checkout** - Pull latest code from repository
2. **Clean & Compile** - Clean and compile the project
3. **Parallel Test Execution**:
   - TestNG Suite
   - Cucumber All Tests  
   - Cucumber Smoke Tests
4. **Generate Reports** - Archive artifacts and generate reports

## Environment Variables

Configure these environment variables in Jenkins:

```bash
MAVEN_OPTS=-Xmx1024m
CHROME_BIN=/usr/bin/google-chrome
JENKINS_URL=http://localhost:8080
```

## Troubleshooting

### Common Issues

**Chrome not found:**
```bash
# Install Chrome on Jenkins agent
wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add -
echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google.list
apt-get update && apt-get install -y google-chrome-stable
```

**Maven not found:**
- Verify Maven is installed and configured in Global Tool Configuration
- Check MAVEN_HOME environment variable

**Tests failing in headless mode:**
- Ensure Chrome arguments include: `--no-sandbox --disable-dev-shm-usage`
- Verify xvfb is available for virtual display

### Debug Mode

Enable debug logging by setting system properties:
```bash
mvn test -Dwebdriver.chrome.verboseLogging=true -Dselenium.LOGGER.level=DEBUG
```

## Best Practices

1. **Resource Management**
   - Use parallel execution for faster feedback
   - Limit concurrent builds to avoid resource conflicts
   - Clean workspace after each build

2. **Test Stability**
   - Implement retry mechanisms for flaky tests
   - Use explicit waits instead of Thread.sleep()
   - Handle test data cleanup properly

3. **Reporting**
   - Archive test artifacts for debugging
   - Include screenshots for failed tests
   - Send notifications on build failures

4. **Security**
   - Use Jenkins credentials for sensitive data
   - Avoid hardcoding URLs or passwords
   - Secure Jenkins instance with proper authentication

## Performance Optimization

- **Parallel Execution**: Run tests in parallel using Maven profiles
- **Docker Agents**: Use containerized agents for isolation
- **Incremental Builds**: Only run affected tests when possible
- **Resource Limits**: Set appropriate memory limits for test execution

## Monitoring and Alerts

Configure notifications for:
- Build failures
- Test failures above threshold
- Long-running builds
- Failed deployments

Example email notification configuration in Jenkinsfile:
```groovy
post {
    failure {
        emailext (
            subject: "Build Failed: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
            body: "Build failed. Check console output at ${env.BUILD_URL}",
            to: "dev-team@company.com"
        )
    }
}
```
