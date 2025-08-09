#!/bin/bash
# Quick Jenkins Test Script
# Place this in Jenkins build step as "Execute shell" (if using Linux) 
# or modify for Windows batch

echo "=== DemoQA Jenkins Test ==="
echo "Java Version:"
java -version

echo "Maven Version:"
mvn -version

echo "Project Directory:"
pwd
ls -la

echo "Running Maven Clean..."
mvn clean

echo "Running Simple Test..."
mvn test -Dtest=TextBoxTests -Dheadless=true -DforkCount=1

echo "Test Results:"
if [ -d "target/surefire-reports" ]; then
    echo "Surefire reports found:"
    ls -la target/surefire-reports/
else
    echo "No surefire reports directory found"
fi

if [ -d "target/reports" ]; then
    echo "ExtentReports found:"
    ls -la target/reports/
else
    echo "No reports directory found"
fi

echo "=== Test Complete ==="
