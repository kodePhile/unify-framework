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

    stage('Update Documentation') {
      steps {
        echo 'Cloning wiki...'
        git clone https://github.com/kodePhile/unify-framework.git >/dev/null 2>&1 # GITHUB_UNIFY_WIKI_URL Format: https://[USERNAME]:[PERSONAL_ACCESS_TOKEN]@github.com/tcdng/unify-framework.wiki.git
        echo 'Wiki clone completed.'
        rm -rf unify-framework.wiki/**
        cp -r docs/* unify-framework.wiki/
        cd unify-framework.wiki
        git add . && git commit -m "Merge Documentation"
        git push origin master
      }
    }
  }
}