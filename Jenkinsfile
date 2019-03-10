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
		stage ( 'Deployment Stage') {
			steps {
				withMaven(maven : 'maven_3.5.4'){
					bat 'mvn deploy'
				  }
			}	
		}
				
	}
}