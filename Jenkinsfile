pipeline {
    agent any

	tools {
		maven 'Maven3'
	}

    stages {
        stage('Source') {
            steps {
                echo "检出源代码"
            }
        }
        stage('Build') {
            steps {
                echo "执行构建"
				sh 'mvn clean package'
            }
        }
        stage('Quality') {
            steps {
                echo "分析代码质量"
            }
        }
        stage('Deploy') {
            steps {
                echo "部署应用"
            }
        }
    }
}