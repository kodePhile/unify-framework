pipeline {
  agent any
  stages {

    stage('Build') {
      steps {
        sh '''
        #!/usr/bin/bash
        echo \'Building...\'
        mvn clean install -DskipTests=true
        echo \'Build successful!\'
        '''
      }
    }


  }
}