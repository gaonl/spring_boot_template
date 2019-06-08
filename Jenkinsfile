pipeline {
    /**
    * 用于指定任务在哪个代理节点上面运行
    **/
    agent any

    options {
        //保留最近5次的构建
        buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '5')
    }

    triggers {
        //轮训版本控制器，每三分钟之内的任意时刻
        pollSCM 'H/3 * * * *'

        //用url触发构建
        GenericTrigger causeString: 'My Generic Cause', genericVariables: [[defaultValue: '', key: 'key1', regexpFilter: '', value: '$.key1']], regexpFilterExpression: '', regexpFilterText: '', token: 'learn_spring_boot'
    }



    /**
    * 用于设置环境变量，可以定义在stage或者pipeline部分
    * MY_PROJECT_NAME 为自定义的环境变量
    * GIT_BRANCH 为 Jenkins内置的环境变量
    **/
    environment {
        MY_PROJECT_NAME = "spring boot test from git: /${GIT_BRANCH}"
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
                //git 'https://github.com/gaonl/spring_boot_template.git'
            }
        }
        stage('CheckStyle') {
            steps {
                echo "CheckStyle验证代码..............."
                //CheckStyle是针对源码进行分析的，所以可以放在编译之前
				sh 'mvn checkstyle:check'
                //sh 'mvn validate'
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
            //junit插件需要junit测试报告文件，所以要放在测试之后
            junit '**/target/surefire-reports/*.xml'


            echo "Jcoco Report..............."
            //jacoco是在字节码中插入锚点来判断代码的运行覆盖，所以需要在编译、测试后生成jacoco报告
            jacoco execPattern: 'target/jacoco.exec'

            echo "Findbugs Report..............."
            //Findbugs分析字节码的，所以需要在编译、测试后生成findbugs报告
            //Note:This goal should be used as a Maven report. 默认的maven生命周期是report，现在手动调用
            sh 'mvn findbugs:findbugs'
            recordIssues(tools: [findBugs(pattern: '**/target/findbugs-out/*.xml', reportEncoding: 'UTF-8', useRankAsPriority: true)])
        }
    }
}