pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9.0' // Configure this name in Jenkins Global Tool Configuration
        jdk 'JDK-11' // Configure this name in Jenkins Global Tool Configuration
    }
    
    environment {
        MAVEN_OPTS = '-Xmx1024m'
        CHROME_BIN = '/usr/bin/google-chrome' // Adjust path for your Jenkins server
    }
    
    parameters {
        choice(
            name: 'TEST_SUITE',
            choices: ['all', 'testng', 'cucumber', 'cucumber-smoke', 'cucumber-regression'],
            description: 'Select which test suite to run'
        )
        booleanParam(
            name: 'HEADLESS_MODE',
            defaultValue: true,
            description: 'Run tests in headless mode'
        )
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out source code...'
                checkout scm
            }
        }
        
        stage('Clean & Compile') {
            steps {
                echo 'Cleaning and compiling the project...'
                sh 'mvn clean compile test-compile'
            }
        }
        
        stage('Run Tests') {
            parallel {
                stage('TestNG Suite') {
                    when {
                        anyOf {
                            params.TEST_SUITE == 'all'
                            params.TEST_SUITE == 'testng'
                        }
                    }
                    steps {
                        echo 'Running TestNG tests...'
                        script {
                            try {
                                sh 'mvn test -Ptestng -Dheadless=${HEADLESS_MODE}'
                            } catch (Exception e) {
                                currentBuild.result = 'UNSTABLE'
                                echo "TestNG tests failed: ${e.getMessage()}"
                            }
                        }
                    }
                }
                
                stage('Cucumber All Tests') {
                    when {
                        anyOf {
                            params.TEST_SUITE == 'all'
                            params.TEST_SUITE == 'cucumber'
                        }
                    }
                    steps {
                        echo 'Running all Cucumber tests...'
                        script {
                            try {
                                sh 'mvn test -Pcucumber -Dheadless=${HEADLESS_MODE}'
                            } catch (Exception e) {
                                currentBuild.result = 'UNSTABLE'
                                echo "Cucumber tests failed: ${e.getMessage()}"
                            }
                        }
                    }
                }
                
                stage('Cucumber Smoke Tests') {
                    when {
                        anyOf {
                            params.TEST_SUITE == 'all'
                            params.TEST_SUITE == 'cucumber-smoke'
                        }
                    }
                    steps {
                        echo 'Running Cucumber smoke tests...'
                        script {
                            try {
                                sh 'mvn test -Pcucumber-smoke -Dheadless=${HEADLESS_MODE}'
                            } catch (Exception e) {
                                currentBuild.result = 'UNSTABLE'
                                echo "Cucumber smoke tests failed: ${e.getMessage()}"
                            }
                        }
                    }
                }
            }
        }
        
        stage('Generate Reports') {
            steps {
                echo 'Generating test reports...'
                script {
                    // Archive test artifacts
                    if (fileExists('target/surefire-reports')) {
                        archiveArtifacts artifacts: 'target/surefire-reports/**/*', fingerprint: true
                    }
                    if (fileExists('target/cucumber-reports')) {
                        archiveArtifacts artifacts: 'target/cucumber-reports/**/*', fingerprint: true
                    }
                    if (fileExists('target/reports')) {
                        archiveArtifacts artifacts: 'target/reports/**/*', fingerprint: true
                    }
                }
            }
        }
    }
    
    post {
        always {
            echo 'Cleaning up workspace...'
            
            // Publish TestNG results
            script {
                if (fileExists('target/surefire-reports/testng-results.xml')) {
                    publishTestResults testResultsPattern: 'target/surefire-reports/testng-results.xml'
                }
            }
            
            // Publish JUnit results (for Cucumber)
            script {
                if (fileExists('target/cucumber-reports/*.xml')) {
                    junit 'target/cucumber-reports/*.xml'
                }
                if (fileExists('target/surefire-reports/TEST-*.xml')) {
                    junit 'target/surefire-reports/TEST-*.xml'
                }
            }
            
            // Publish HTML reports
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/reports',
                reportFiles: '*.html',
                reportName: 'ExtentReports',
                reportTitles: 'Test Execution Report'
            ])
            
            // Publish Cucumber HTML reports
            script {
                if (fileExists('target/cucumber-reports')) {
                    publishHTML([
                        allowMissing: true,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'target/cucumber-reports',
                        reportFiles: '*.html',
                        reportName: 'CucumberReports',
                        reportTitles: 'Cucumber Test Report'
                    ])
                }
            }
            
            // Clean workspace
            cleanWs()
        }
        
        success {
            echo 'All tests passed successfully!'
            // You can add email notifications here
        }
        
        failure {
            echo 'Build failed!'
            // You can add email notifications here
        }
        
        unstable {
            echo 'Some tests failed, but build completed.'
            // You can add email notifications here
        }
    }
}
