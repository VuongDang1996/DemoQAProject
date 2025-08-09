#!/bin/bash

# Jenkins Local Setup Script
# This script helps you set up Jenkins locally using Docker

set -e

echo "=== Jenkins Local Setup for DemoQA Project ==="

# Check if Docker is installed
check_docker() {
    if ! command -v docker &> /dev/null; then
        echo "❌ Docker is not installed. Please install Docker first:"
        echo "   Windows: https://docs.docker.com/desktop/windows/install/"
        echo "   Mac: https://docs.docker.com/desktop/mac/install/"
        echo "   Linux: https://docs.docker.com/engine/install/"
        exit 1
    fi
    
    if ! command -v docker-compose &> /dev/null; then
        echo "❌ Docker Compose is not installed. Please install Docker Compose first."
        exit 1
    fi
    
    echo "✅ Docker and Docker Compose are installed"
}

# Start Jenkins using Docker Compose
start_jenkins() {
    echo "🚀 Starting Jenkins with Docker Compose..."
    
    # Create jenkins directory if it doesn't exist
    mkdir -p jenkins
    
    # Start Jenkins
    docker-compose up -d jenkins
    
    echo "📝 Jenkins is starting up..."
    echo "🌐 URL: http://localhost:8080"
    echo "⏳ Please wait 2-3 minutes for Jenkins to fully start"
    
    # Wait for Jenkins to start
    echo "Waiting for Jenkins to be ready..."
    sleep 30
    
    # Get initial admin password
    echo "🔑 Getting initial admin password..."
    docker exec demoqa-jenkins cat /var/jenkins_home/secrets/initialAdminPassword 2>/dev/null || echo "Password will be available once Jenkins fully starts"
}

# Install Jenkins using Java (Alternative method)
install_jenkins_java() {
    echo "📦 Alternative: Installing Jenkins using Java..."
    echo "1. Download Jenkins WAR file:"
    echo "   wget https://get.jenkins.io/war-stable/latest/jenkins.war"
    echo ""
    echo "2. Run Jenkins:"
    echo "   java -jar jenkins.war --httpPort=8080"
    echo ""
    echo "3. Access Jenkins at: http://localhost:8080"
}

# Setup Jenkins plugins and configuration
setup_jenkins_config() {
    echo "🔧 Jenkins Configuration Steps:"
    echo ""
    echo "1. Open http://localhost:8080 in your browser"
    echo "2. Enter the initial admin password (shown above)"
    echo "3. Install suggested plugins + additional plugins:"
    echo "   - Git plugin"
    echo "   - Maven Integration plugin"
    echo "   - HTML Publisher plugin"
    echo "   - JUnit plugin"
    echo "   - Pipeline plugin"
    echo "   - GitHub plugin"
    echo "   - Build Timeout plugin"
    echo ""
    echo "4. Create admin user"
    echo "5. Configure Jenkins URL: http://localhost:8080"
    echo ""
    echo "6. Configure Global Tools (Manage Jenkins > Global Tool Configuration):"
    echo "   - JDK: Add JDK-11"
    echo "   - Maven: Add Maven-3.9.0"
    echo "   - Git: Default"
}

# Create Jenkins job
create_jenkins_job() {
    echo "📋 Creating Jenkins Job:"
    echo ""
    echo "Method 1: Pipeline Job (Recommended)"
    echo "1. Click 'New Item' in Jenkins"
    echo "2. Enter name: 'DemoQA-Pipeline'"
    echo "3. Select 'Pipeline' and click OK"
    echo "4. In Pipeline section:"
    echo "   - Definition: Pipeline script from SCM"
    echo "   - SCM: Git"
    echo "   - Repository URL: https://github.com/VuongDang1996/DemoQAProject.git"
    echo "   - Script Path: Jenkinsfile"
    echo "5. Save and Build"
    echo ""
    echo "Method 2: Import Freestyle Job"
    echo "1. Use the job-config.xml file in jenkins/ directory"
    echo "2. Create new Freestyle project"
    echo "3. Import configuration from XML"
}

# Display useful commands
display_commands() {
    echo "🛠️  Useful Docker Commands:"
    echo ""
    echo "Start Jenkins:"
    echo "  docker-compose up -d jenkins"
    echo ""
    echo "Stop Jenkins:"
    echo "  docker-compose down"
    echo ""
    echo "View Jenkins logs:"
    echo "  docker logs demoqa-jenkins"
    echo ""
    echo "Get admin password:"
    echo "  docker exec demoqa-jenkins cat /var/jenkins_home/secrets/initialAdminPassword"
    echo ""
    echo "Access Jenkins shell:"
    echo "  docker exec -it demoqa-jenkins bash"
    echo ""
    echo "Restart Jenkins:"
    echo "  docker restart demoqa-jenkins"
}

# Main execution
main() {
    echo "Choose setup method:"
    echo "1. Docker (Recommended)"
    echo "2. Java WAR file"
    echo "3. Show useful commands"
    echo ""
    
    read -p "Enter your choice (1-3): " choice
    
    case $choice in
        1)
            check_docker
            start_jenkins
            setup_jenkins_config
            create_jenkins_job
            display_commands
            ;;
        2)
            install_jenkins_java
            setup_jenkins_config
            create_jenkins_job
            ;;
        3)
            display_commands
            ;;
        *)
            echo "Invalid choice. Please run the script again."
            exit 1
            ;;
    esac
}

main "$@"
