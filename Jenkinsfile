pipeline {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-21' // Maven + JDK 21 image from Docker Hub
            args '-v /root/.m2:/root/.m2'         // Mount local Maven repo for caching
        }
    }

    environment {
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=false"
    }

    stages {
        stage('Checkout') {
            steps {
                echo "Checking out source code..."
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo "Building with Maven + JDK 21 (inside Docker)..."
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo "Running tests..."
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                echo "Packaging app..."
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo "Deploying artifact..."
                sh 'mvn deploy'
            }
        }
    }

    post {
        success {
            echo "Build succeeded inside Docker with JDK 21!"
        }
        failure {
            echo "Build failed!"
        }
    }
}
// Jenkinsfile for building a Java application with Maven and JDK 21 inside a Docker container