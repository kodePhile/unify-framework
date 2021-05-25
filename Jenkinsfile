pipeline {
  agent any

  tools {
    maven 'Maven3.6.3'
  }

  stages {

    stage('Build') {
      steps {
        echo 'Building Project...'
        sh 'mvn clean install'
        echo 'Build successful!'
      }
    }

    stage('Update Documentation') {
      steps {
        echo 'Cloning Wiki...'
        git 'https://github.com/kodePhile/unify-framework.wiki.git'
        echo 'Wiki clone successful!'
        dir('unify-framework.wiki') {
          deleteDir()
          sh '''
            cp -r ../docs .
            git add .
            commit -m "Update documentation" && git push
          '''
        }
      }
    }
  }
}