pipeline {
    agent none

    stages {
        stage('Check Dev Branch') {
            agent { label 'dev-node' }
            steps {
                echo 'Checking Dev branch...'
                git branch: 'dev', url: 'https://github.com/Arnavjain2503/jenkins-multistage-demo'
                bat 'cd demo && mvn clean compile'
            }
        }

        stage('Check API Branch') {
            agent { label 'api-node' }
            steps {
                echo 'Checking Feature-API branch...'
                git branch: 'feature-api', url: 'https://github.com/Arnavjain2503/jenkins-multistage-demo'
                bat 'cd demo && mvn clean compile'
            }
        }

        stage('Check UI Branch') {
            agent { label 'ui-node' }
            steps {
                echo 'Checking Feature-UI branch...'
                git branch: 'feature-ui', url: 'https://github.com/Arnavjain2503/jenkins-multistage-demo'
                bat 'cd demo && mvn clean compile'
            }
        }

        stage('Merge and Build') {
            agent { label 'dev-node' }
            steps {
                echo 'All branches passed. Proceeding to merge...'

                git branch: 'dev', url: 'https://github.com/Arnavjain2503/jenkins-multistage-demo'

                bat '''
                    git config user.name "Jenkins"
                    git config user.email "jenkins@example.com"

                    git fetch origin feature-api
                    git fetch origin feature-ui

                    echo Merging feature-api into dev...
                    git merge origin/feature-api --no-edit

                    echo Merging feature-ui into dev...
                    git merge origin/feature-ui --no-edit
                '''

                echo 'Building merged project (creating JAR/WAR)...'
                bat 'cd demo && mvn clean package -DskipTests'
            }
        }

        stage('Run Application') {
            agent { label 'dev-node' }
            steps {
                echo 'Starting application on port 9090...'
                bat '''
                    cd demo
                    for /f "delims=" %%F in ('dir /b target\\*.jar') do (
                        echo Starting %%F on port 9090
                        start /b java -jar target\\%%F --server.port=9090
                        goto :done
                    )
                    :done
                '''
                echo 'Application started! Visit http://localhost:9090'
            }
        }
    }

    post {
        success {
            echo 'All branches validated, merged, built, and deployed successfully on port 9090!'
        }
        failure {
            echo 'One or more stages failed. Merge or build aborted.'
        }
    }
}
