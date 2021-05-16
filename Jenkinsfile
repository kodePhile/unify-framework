pipeline {
  agent any
  stages {
    stage('Build') {
      steps{
        echo 'Building...'
        mvn clean install -DskipTests=true
        echo 'Build successful!'
      }
    }

  }
}