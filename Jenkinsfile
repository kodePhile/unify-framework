pipeline {
  agent any

  tools {
    maven 'Maven3.6.3'
  }

  stages {

    stage('Build') {
      echo 'Building Project...'
      sh 'mvn clean install'
      echo 'Build successful!'
    }

    stage('Update Documentation') {
      echo 'Cloning Wiki...'
      git 'https://github.com/kodePhile/unify-framework.wiki.git'
      echo 'Wiki clone successful!'
      dir('unify-framework.wiki') {
        deleteDir()
        sh '''
          cp -r ../docs .
          git add .
          git commit -m "Update documentation" && git push
        '''
      }
    }
  }
}