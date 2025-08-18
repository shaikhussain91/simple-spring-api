// define the pipeline using the declarative syntax
// we use Jenkinsfile to define the pipeline
pipeline {
    
    // use jenkins node as the agent
    // this means that the pipeline can run on any available agent
    agent any

    tools {
        jdk 'jdk-21'
        maven 'maven-3.8.4'
    }

    environment {
        // define the SonarQube server URL and credentials
        SONARQUBE_SERVER = 'SonarCloud'
        SONAR_PROJECT_KEY = 'shaikhussain91'
        SONAR_PROJECT_NAME = 'shaikhussain91_simple-spring-api'
        SONAR_ORGANIZATION = 'shaikhussain91'
    }

    stages {
        // stage to checkout the code from the repository
         stage('Checkout') {
            steps {
                echo 'Cloning repository...'
                // by default jenkins will use the master branch
                // if you want to use a different branch, specify it here
                git branch: 'main', url: 'https://github.com/shaikhussain91/simple-spring-api.git'
            }
        }

        // stage to build the application using maven
        stage('Build') {
            steps {
                echo 'Building with Maven...'
                sh 'mvn clean install'
            }
        }

        // stage to run tests
        // this stage will run the tests using maven
        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
            // post actions for the test stage
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }

        // stage to perform static code analysis using SonarQube
        stage('SonarQube Analysis') {
            steps {
                echo 'Running SonarQube analysis...'
                withSonarQubeEnv(SONARQUBE_SERVER) {
                    sh """
                        mvn sonar:sonar \
                        -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                        -Dsonar.organization=${SONAR_ORGANIZATION} \
                        -Dsonar.projectName=${SONAR_PROJECT_NAME} \
                    """
                }
            }
            // post actions for the SonarQube analysis stage
            post {
                always {
                    echo 'SonarQube analysis completed.'
                }
            }
        }

        stage('Quality Gate Check') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    echo 'Waiting for SonarQube quality gate...'
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        // stage to deploy the application
        // this stage will deploy the application to a server
        stage('Deploy') {
            steps {
                echo 'Deploying to a server...'
                echo 'Deployment successful!'
            }
        }
    }

    post {
        // post actions for the entire pipeline
        always {
            echo 'Pipeline succeeded with status: ${currentBuild.currentResult}'
        }
    }

}
