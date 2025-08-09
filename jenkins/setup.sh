#!/bin/bash

# Jenkins Setup Script for DemoQA Project
# This script helps set up Jenkins job for the DemoQA testing framework

echo "=== Jenkins Setup for DemoQA Project ==="

# Function to create Jenkins job using Jenkins CLI
create_jenkins_job() {
    local job_name="DemoQA-Test-Automation"
    local jenkins_url="${JENKINS_URL:-http://localhost:8080}"
    local jenkins_user="${JENKINS_USER:-admin}"
    local jenkins_token="${JENKINS_TOKEN}"
    
    if [ -z "$jenkins_token" ]; then
        echo "Warning: JENKINS_TOKEN not set. You'll need to authenticate manually."
    fi
    
    echo "Creating Jenkins job: $job_name"
    echo "Jenkins URL: $jenkins_url"
    
    # Check if Jenkins CLI is available
    if command -v jenkins-cli &> /dev/null; then
        echo "Using Jenkins CLI to create job..."
        jenkins-cli -s "$jenkins_url" -auth "$jenkins_user:$jenkins_token" create-job "$job_name" < jenkins/job-config.xml
    else
        echo "Jenkins CLI not found. Please create the job manually using the provided XML configuration."
        echo "Job configuration file: jenkins/job-config.xml"
    fi
}

# Function to install required Jenkins plugins
install_jenkins_plugins() {
    echo "Required Jenkins plugins:"
    echo "- Git plugin"
    echo "- Maven Integration plugin"
    echo "- HTML Publisher plugin"
    echo "- JUnit plugin"
    echo "- Pipeline plugin"
    echo "- GitHub plugin"
    echo "- Build Timeout plugin"
    echo "- Workspace Cleanup plugin"
    echo ""
    echo "Install these plugins through Jenkins > Manage Jenkins > Manage Plugins"
}

# Function to display tool configuration instructions
display_tool_config() {
    echo "=== Jenkins Tool Configuration ==="
    echo "Go to Jenkins > Manage Jenkins > Global Tool Configuration"
    echo ""
    echo "1. JDK Configuration:"
    echo "   - Name: JDK-11"
    echo "   - JAVA_HOME: /usr/lib/jvm/java-11-openjdk-amd64 (or your Java 11 path)"
    echo ""
    echo "2. Maven Configuration:"
    echo "   - Name: Maven-3.9.0"
    echo "   - MAVEN_HOME: /opt/maven (or your Maven path)"
    echo ""
    echo "3. Git Configuration:"
    echo "   - Name: Default"
    echo "   - Path to Git executable: git"
}

# Function to display pipeline instructions
display_pipeline_instructions() {
    echo "=== Pipeline Job Setup ==="
    echo "1. In Jenkins, click 'New Item'"
    echo "2. Enter job name: 'DemoQA-Pipeline'"
    echo "3. Select 'Pipeline' and click OK"
    echo "4. In Pipeline section, select 'Pipeline script from SCM'"
    echo "5. SCM: Git"
    echo "6. Repository URL: https://github.com/VuongDang1996/DemoQAProject.git"
    echo "7. Script Path: Jenkinsfile"
    echo "8. Save the job"
}

# Function to display Docker instructions
display_docker_instructions() {
    echo "=== Docker Agent Setup ==="
    echo "1. Build the Docker image:"
    echo "   cd jenkins/"
    echo "   docker build -t demoqa-jenkins-agent ."
    echo ""
    echo "2. Run the container:"
    echo "   docker run -d --name demoqa-agent demoqa-jenkins-agent"
    echo ""
    echo "3. Configure in Jenkins:"
    echo "   - Go to Manage Jenkins > Manage Nodes and Clouds"
    echo "   - Add Docker Cloud configuration"
    echo "   - Use the demoqa-jenkins-agent image"
}

# Function to display webhook setup
display_webhook_instructions() {
    echo "=== GitHub Webhook Setup ==="
    echo "1. Go to your GitHub repository: https://github.com/VuongDang1996/DemoQAProject"
    echo "2. Go to Settings > Webhooks"
    echo "3. Click 'Add webhook'"
    echo "4. Payload URL: http://your-jenkins-url/github-webhook/"
    echo "5. Content type: application/json"
    echo "6. Select 'Just the push event'"
    echo "7. Click 'Add webhook'"
}

# Main execution
echo "Starting Jenkins setup for DemoQA Project..."
echo ""

install_jenkins_plugins
echo ""

display_tool_config
echo ""

display_pipeline_instructions
echo ""

display_docker_instructions
echo ""

display_webhook_instructions
echo ""

echo "=== Setup Complete ==="
echo "Your Jenkins configuration files are ready!"
echo "Files created:"
echo "- Jenkinsfile (Pipeline configuration)"
echo "- jenkins/job-config.xml (Freestyle job configuration)"
echo "- jenkins/Dockerfile (Docker agent configuration)"
echo ""
echo "Next steps:"
echo "1. Install required Jenkins plugins"
echo "2. Configure tools (JDK, Maven, Git)"
echo "3. Create Jenkins job using provided configurations"
echo "4. Set up GitHub webhook for automatic triggers"
echo ""
echo "For pipeline job, use the Jenkinsfile in the root directory."
echo "For freestyle job, import the jenkins/job-config.xml configuration."
