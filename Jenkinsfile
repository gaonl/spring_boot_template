pipeline {
    /**
    * 用于指定任务在哪个代理节点上面运行
    **/
    agent any
    
    options {
        buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '3')
    }


    /**
    * 用于设置环境变量，可以定义在stage或者pipeline部分
    * MY_PROJECT_NAME 为自定义的环境变量
    * GIT_BRANCH 为 Jenkins内置的环境变量
    **/
    environment {
        MY_PROJECT_NAME = "spring boot test: ${GIT_BRANCH}"
    }

    /**
    * 用于指定自动安装工具，比如maven等；可以在页面上事先配置好，并用名称引用
    **/
	tools {
		maven 'Maven3'
	}

    /**
    * 构建的步骤
    **/
    stages {
        stage('Print env') {
            steps {
                //打印出环境变量
                sh "printenv"
            }
        }
        stage('Source') {
            steps {
                echo "从 ${MY_PROJECT_NAME} 检出源代码..............."
            }
        }
        stage('CheckStyle') {
            steps {
                echo "CheckStyle验证代码..............."
                sh 'mvn validate'
            }
            post {
                always {
                    /**
                    * checkstyle
                    **/
                    recordIssues enabledForFailure: true, referenceJobName: 'spring_boot_template_pipeline', sourceCodeEncoding: 'UTF-8', tools: [checkStyle(reportEncoding: 'UTF-8')]
                }
            }
        }
        stage('Build') {
            steps {
                echo "执行构建..............."
				sh 'mvn clean package'
            }
        }
        stage('Deploy') {
            steps {
                echo "部署应用..............."
            }
        }
    }

    /**
    * 构建后操作，可以放在stage或者pipeline部分中
    **/
    post {
        success {
            echo "pipeline success..............."
        }
        always {
            echo "Test Report..............."
            junit '**/target/surefire-reports/*.xml'
        }
    }
}