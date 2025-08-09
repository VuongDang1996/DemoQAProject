@echo off
REM Quick Jenkins Test Script for Windows
REM Use this in Jenkins build step as "Execute Windows batch command"

echo === DemoQA Jenkins Test ===
echo Java Version:
java -version

echo Maven Version:
mvn -version

echo Project Directory:
cd
dir

echo Running Maven Clean...
mvn clean

echo Running Simple Test...
mvn test -Dtest=TextBoxTests -Dheadless=true -DforkCount=1

echo Test Results:
if exist "target\surefire-reports" (
    echo Surefire reports found:
    dir target\surefire-reports\
) else (
    echo No surefire reports directory found
)

if exist "target\reports" (
    echo ExtentReports found:
    dir target\reports\
) else (
    echo No reports directory found
)

echo === Test Complete ===
