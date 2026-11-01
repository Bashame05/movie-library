pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: ''https://github.com/Bashame05/movie-library.git
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                echo 'WAR file generated. Ready for Tomcat deployment.'
            }
        }
    }
}
