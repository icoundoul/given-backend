@Library('jenkins-shared-libraries')
pipeline{
	agent any;
	stages {
		stage ( 'Distribution') {
		paralele (
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
				
				stage ( 'Deploy to nexus') {
					steps {
							echo "mv clean install --settings ${SETTINGS_PATH}"	
					}
				}
		   }
	}
	post {
		success { 
			echo "Job Ok ;)..."	
		}
		failure {
			echo "Problème du job..."		
		}
	}
}