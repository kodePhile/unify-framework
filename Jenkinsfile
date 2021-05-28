pipeline {
  agent {
    docker {
      image 'ubuntu'
    }
  }

  tools {
    maven 'Maven3.6.3'
  }

  stages {

    stage('Build') {
      steps {
        echo 'Building Project...'
        sh 'sudo mkdir /logs && cd /logs'
        sh 'touch application.log && cd ..'
        sh 'mvn clean install'
        echo 'Build successful!'
      }
    }

    stage('Update Documentation') {
      steps {
        echo 'Cloning Wiki...'
        echo 'Current Directory: $pwd'
        git 'https://github.com/kodePhile/unify-framework.wiki.git'
        echo 'Wiki clone successful!'
        dir('unify-framework.wiki') {
          deleteDir()
          sh '''
            ls -lart
            cp -r ../docs .
            git add .
            git commit -m "Update documentation" && git push
          '''
        }
      }
    }
  }
}