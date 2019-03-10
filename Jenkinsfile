pipeline{
	agent any;
	stages {
		stage ( 'Compile Stage') {
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
		//stage ( 'Deployment Stage') {
		//	steps {
		//		withMaven(maven : 'maven_3.5.4'){
		//			bat 'mvn deploy'
		//		  }
		//	}	
		//}
				
				
			 
		stage ( 'Job infos') {
			steps {
					echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"	
			}
		}


	stage ( 'Test variables') {
			steps {
					step {
						def username = 'Jenkins'
						echo 'Hello Mr. ${username}'
						echo "I said, Hello Mr. ${username}"
					}
			}
		}
	}	 
	
}