pipeline {
    agent {
        docker {
            image 'gradle:8.2.1-jdk17-alpine'
        }
    }

    environment {
        MYSQL_URL = 'jdbc:mysql://mysql:3306/user_auth_database'  // URL to access MySQL container
        MYSQL_USER = 'root'
        MYSQL_PASSWORD = 'pranshubarar'
    }

    stages {
        stage('Start MySQL Container') {
            steps {
                script {
                    docker.image('mysql:8.0').withRun('-e MYSQL_ROOT_PASSWORD=pranshubarar -e MYSQL_DATABASE=user_auth_database --name mysql -p 3306:3306') { c ->
                        // MySQL container is up and running
                    }
                }
            }
        }
        stage('Build Authorization Server') {
            steps {
                dir('authorizationserverv4') {
                    sh './gradlew build'
                }
            }
        }
        stage('Start Authorization Server') {
            steps {
                dir('authorizationserverv4') {
                    sh './gradlew bootRun &'
                }
            }
        }
        stage('Build Resource Server') {
            steps {
                dir('resourceserverv4') {
                    sh './gradlew build'
                }
            }
        }
        stage('Start Resource Server') {
            steps {
                dir('resourceserverv4') {
                    sh './gradlew bootRun &'
                }
            }
        }
        stage('Build OAuth2 Client') {
            steps {
                dir('clientv4') {
                    sh './gradlew build'
                }
            }
        }
        stage('Start OAuth2 Client') {
            steps {
                dir('clientv4') {
                    sh './gradlew bootRun &'
                }
            }
        }
    }

    post {
        always {
            script {
                // Cleanup any running containers after the pipeline finishes
                docker.image('mysql:8.0').inside {
                    sh 'docker stop mysql || true'
                    sh 'docker rm mysql || true'
                }
            }
        }
    }
}

