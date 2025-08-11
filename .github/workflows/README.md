# GitHub Actions Configuration

This directory contains GitHub Actions workflows for the DemoQA Test Automation Framework.

## Available Workflows

### 1. `ci-cd-pipeline.yml` - Main CI/CD Pipeline
**Triggers:**
- Push to `main` or `develop` branches
- Pull requests to `main` or `develop`
- Scheduled runs (daily at 6 AM UTC)
- Manual dispatch with options

**Features:**
- Multi-browser testing (Chrome, Firefox)
- Parallel test execution
- TestNG and Cucumber framework support
- Artifact uploading and reporting
- Test result publishing
- GitHub Pages deployment for reports

### 2. `pr-validation.yml` - Pull Request Validation
**Triggers:**
- Pull request opened, synchronized, or reopened

**Features:**
- Fast smoke tests for PR validation
- Code quality checks
- Automated PR comments with results
- Quick feedback for developers

### 3. `nightly-tests.yml` - Comprehensive Nightly Tests
**Triggers:**
- Scheduled nightly runs (2 AM UTC)
- Manual dispatch

**Features:**
- Full regression test suite
- Extended timeout (2 hours)
- Cross-browser testing
- Failure investigation with screenshots
- Automatic issue creation on failures
- Consolidated reporting

### 4. `release-validation.yml` - Release Testing
**Triggers:**
- Release published/created
- Version tags (v*.*.*, release-*)

**Features:**
- Comprehensive release validation
- Multi-environment testing (staging, production)
- Cross-browser compatibility (Chrome, Firefox, Edge)
- Extended artifact retention (90 days)
- Deployment readiness checks
- Release report generation

## Workflow Configuration

### Environment Variables
```yaml
MAVEN_OPTS: -Xmx1024m
JAVA_VERSION: '17'
NODE_VERSION: '18'
```

### Secrets Required
The workflows use the following GitHub secrets:
- `GITHUB_TOKEN` - Automatically provided by GitHub
- Add additional secrets for integrations (Slack, email, etc.)

## Browser Support

| Browser | Supported | Notes |
|---------|-----------|-------|
| Chrome  | ✅        | Primary browser for testing |
| Firefox | ✅        | Cross-browser compatibility |
| Edge    | ✅        | Release validation only |

## Test Execution Matrix

### CI/CD Pipeline
- **Browsers:** Chrome, Firefox
- **Test Groups:** Smoke, Regression, Full
- **Frameworks:** TestNG, Cucumber

### Nightly Tests
- **Browsers:** Chrome, Firefox
- **Test Types:** TestNG, Cucumber
- **Timeout:** 120 minutes

### Release Validation
- **Browsers:** Chrome, Firefox, Edge
- **Environments:** Staging, Production
- **Timeout:** 180 minutes

## Artifact Management

### Retention Policies
- **PR Validation:** 7 days
- **CI/CD Pipeline:** 30 days
- **Nightly Tests:** 7 days (failures: 30 days)
- **Release Validation:** 90 days (reports: 365 days)

### Artifact Types
- Test reports (HTML/XML)
- Screenshots (failures)
- Consolidated reports
- Performance metrics

## Notifications

The workflows support notifications for:
- Test completion (success/failure)
- Release validation results
- Nightly test failures

**Integration Points:**
- GitHub Issues (automatic creation)
- Pull Request comments
- GitHub Pages deployment
- Custom notification services (Slack, Teams, Email)

## Manual Workflow Dispatch

All workflows support manual triggering with customizable parameters:

### CI/CD Pipeline
- Test suite selection (full, smoke, regression, testng, cucumber)
- Browser selection (chrome, firefox)
- Environment selection (staging, production)

### Nightly Tests
- Full regression toggle
- Notification preferences

### Release Validation
- Automatic based on release/tag events

## Setup Instructions

1. **Enable GitHub Actions** in your repository settings
2. **Configure branch protection** for main/develop branches
3. **Set up GitHub Pages** for report hosting (optional)
4. **Add required secrets** for integrations
5. **Customize notification settings** in workflow files

## Performance Optimization

### Caching Strategy
- Maven dependencies cached using `hashFiles('**/pom.xml')`
- Cache restored across workflow runs
- Reduces build time by ~2-3 minutes

### Parallel Execution
- Matrix strategy for browser/test combinations
- Fork count optimized per environment
- Resource allocation based on runner capacity

### Resource Limits
- Standard GitHub runners (ubuntu-latest)
- Memory allocation: 1-2GB per workflow
- Timeout limits prevent hanging jobs

## Monitoring and Troubleshooting

### Common Issues
1. **Browser setup failures** - Check browser action versions
2. **Test timeouts** - Adjust timeout values in workflows
3. **Artifact upload issues** - Verify path configurations
4. **Cache issues** - Clear cache by updating pom.xml

### Debugging
- Use `actions/upload-artifact` for debugging information
- Enable debug logging with `ACTIONS_STEP_DEBUG: true`
- Check runner logs for detailed error information

### Best Practices
- Keep workflows focused and modular
- Use appropriate timeouts
- Implement proper error handling
- Regular maintenance of dependencies

---

**Note:** These workflows are designed to work with the DemoQA Test Automation Framework structure. Customize paths and commands based on your specific project setup.
