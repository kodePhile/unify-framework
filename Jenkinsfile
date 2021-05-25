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

    stage('Update Documentation') {
      steps {
        echo 'Cloning unify-framework.wiki...'
        git 'https://github.com/kodePhile/unify-framework.wiki.git'
        echo 'unify-framework.wiki clone completed.'
        rm -rf unify-framework.wiki/**
        cp -r docs/* unify-framework.wiki/
        cd unify-framework.wiki
        git add . && git commit -m "Merge Documentation"
        git push origin master
      }
    }
  }
}