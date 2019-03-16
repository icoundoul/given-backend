@Library('jenkins-shared-libraries')
pipeline{
	agent any;
	stages {
		stage ( 'Distribution') {
			steps {
				withMaven(maven : 'maven_3.5.4'){
					bat 'mvn clean install'
				  }
			}	
		}
		stage ( 'Testing Stage') {
			steps {
				withMaven(maven : 'maven_3.5.4'){
					bat 'mvn test'
				  }
			}	
		}
			 
		stage ( 'Job infos') {
			steps {
					echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"	
			}
		}
   }

	post {
		success { 
			echo "Job ok..."	
		}
		failure {
			echo "Job ko!!! ..."		
		}
	}
}