pipeline {
  agent any
  stages {

    stage('Build') {
      steps {
        echo 'Building...'
        withEnv( ["PATH+MAVEN=${tool name: 'Maven3.6.3', type: 'maven'}/bin"] ) {
          sh "mvn clean install -DskipTests=true"
        }
        echo 'Build successful!'
      }
    }


  }
}