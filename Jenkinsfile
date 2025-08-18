pipeline {
    agent any

    tools {
        jdk 'jdk-21'
        maven 'maven-3.8.4'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning repository...'
                git branch: 'main', url: 'https://github.com/ashish-panicker/simple-spring-api.git'
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
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying to a server...'
                echo 'Deployment successful!'
            }
        }
    }
}