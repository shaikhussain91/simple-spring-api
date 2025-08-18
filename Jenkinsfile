pipeline {
    agent any

    tools {
        // Specify the JDK and Maven tool to be used
        // These must be configured in Jenkins' Global Tool Configuration
        jdk 'JDK 21'
        maven 'Maven 3.8.4'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning repository...'
                git 'https://github.com/ashish-panicker/simple-spring-api.git'
            }
        }
        stage('Build') {
            steps {
                echo 'Building with Maven...'
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying to a server...'
                // A real-world deploy step would copy the artifact
                // and start the application on a remote server.
                // For example, using a shell script or a plugin.
                echo 'Deployment successful!'
            }
        }
    }
}