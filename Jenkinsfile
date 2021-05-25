pipeline {
  agent any

  tools {
    maven 'Maven3.6.3'
  }

  stages {
    stage('Build') {
      steps {
        echo 'Building...'
        sh "mvn clean install"
        echo 'Build successful!'
      }
    }
  }
}