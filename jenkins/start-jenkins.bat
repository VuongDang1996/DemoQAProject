@echo off
REM Jenkins Setup Script for Windows
REM This script helps you set up Jenkins locally on Windows

echo === Jenkins Local Setup for DemoQA Project (Windows) ===

:check_requirements
echo Checking requirements...

REM Check if Docker Desktop is running
docker version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Docker is not running. Please start Docker Desktop first.
    echo Download from: https://docs.docker.com/desktop/windows/install/
    pause
    exit /b 1
)

docker-compose version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ Docker Compose is not available. Please install Docker Desktop with Compose.
    pause
    exit /b 1
)

echo ✅ Docker and Docker Compose are available

:menu
echo.
echo Choose setup method:
echo 1. Start Jenkins with Docker (Recommended)
echo 2. Download and run Jenkins WAR file
echo 3. Show useful commands
echo 4. Exit
echo.
set /p choice="Enter your choice (1-4): "

if "%choice%"=="1" goto start_docker
if "%choice%"=="2" goto start_war
if "%choice%"=="3" goto show_commands
if "%choice%"=="4" goto end
echo Invalid choice. Please try again.
goto menu

:start_docker
echo.
echo 🚀 Starting Jenkins with Docker Compose...

REM Start Jenkins using docker-compose
docker-compose up -d jenkins

if %errorlevel% neq 0 (
    echo ❌ Failed to start Jenkins. Check Docker Desktop is running.
    pause
    goto menu
)

echo.
echo 📝 Jenkins is starting up...
echo 🌐 URL: http://localhost:8080
echo ⏳ Please wait 2-3 minutes for Jenkins to fully start

REM Wait for Jenkins to start
echo Waiting for Jenkins to be ready...
timeout /t 30 /nobreak >nul

echo.
echo 🔑 Getting initial admin password...
docker exec demoqa-jenkins cat /var/jenkins_home/secrets/initialAdminPassword 2>nul
if %errorlevel% neq 0 (
    echo Password will be available once Jenkins fully starts
    echo Run: docker exec demoqa-jenkins cat /var/jenkins_home/secrets/initialAdminPassword
)

goto setup_instructions

:start_war
echo.
echo 📦 Alternative: Installing Jenkins using Java WAR file...
echo.
echo Manual steps:
echo 1. Download Jenkins WAR file:
echo    https://get.jenkins.io/war-stable/latest/jenkins.war
echo.
echo 2. Run Jenkins:
echo    java -jar jenkins.war --httpPort=8080
echo.
echo 3. Access Jenkins at: http://localhost:8080
echo.
pause
goto setup_instructions

:setup_instructions
echo.
echo 🔧 Jenkins Configuration Steps:
echo.
echo 1. Open http://localhost:8080 in your browser
echo 2. Enter the initial admin password (shown above)
echo 3. Install suggested plugins + additional plugins:
echo    - Git plugin
echo    - Maven Integration plugin
echo    - HTML Publisher plugin
echo    - JUnit plugin
echo    - Pipeline plugin
echo    - GitHub plugin
echo    - Build Timeout plugin
echo.
echo 4. Create admin user
echo 5. Configure Jenkins URL: http://localhost:8080
echo.
echo 6. Configure Global Tools (Manage Jenkins ^> Global Tool Configuration):
echo    - JDK: Add JDK-11 (path: C:\Program Files\Java\jdk-11.*)
echo    - Maven: Add Maven-3.9.0 (auto-install or specify path)
echo    - Git: Default (should auto-detect)
echo.
echo 📋 Creating Jenkins Job:
echo.
echo Method 1: Pipeline Job (Recommended)
echo 1. Click 'New Item' in Jenkins
echo 2. Enter name: 'DemoQA-Pipeline'
echo 3. Select 'Pipeline' and click OK
echo 4. In Pipeline section:
echo    - Definition: Pipeline script from SCM
echo    - SCM: Git
echo    - Repository URL: https://github.com/VuongDang1996/DemoQAProject.git
echo    - Script Path: Jenkinsfile
echo 5. Save and Build
echo.
pause
goto show_commands

:show_commands
echo.
echo 🛠️  Useful Docker Commands:
echo.
echo Start Jenkins:
echo   docker-compose up -d jenkins
echo.
echo Stop Jenkins:
echo   docker-compose down
echo.
echo View Jenkins logs:
echo   docker logs demoqa-jenkins
echo.
echo Get admin password:
echo   docker exec demoqa-jenkins cat /var/jenkins_home/secrets/initialAdminPassword
echo.
echo Access Jenkins shell:
echo   docker exec -it demoqa-jenkins bash
echo.
echo Restart Jenkins:
echo   docker restart demoqa-jenkins
echo.
echo Jenkins URL: http://localhost:8080
echo.
pause
goto menu

:end
echo.
echo Thank you for using Jenkins setup script!
echo Jenkins should be running at: http://localhost:8080
pause
