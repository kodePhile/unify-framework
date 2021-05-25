pipeline {
  agent any
  stages {

    stage('Build') {
      steps {
        echo 'Building...'
        def mvnHome = tool name: 'Maven 3.6.3', type: 'maven'
        sh "${mvnHome}/bin/mvn clean install -DskipTests=true"
        echo 'Build successful!'
      }
    }


  }
}