pipeline {
    agent any
    
	tools {
		maven 'maven-3.6.1'
	}
	
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
				sh 'echo "success"'
            }
        }
    }
}