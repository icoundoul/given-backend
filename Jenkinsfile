pipeline{
	agent any;
	stages {
		stage ( 'Compile Stage') {
			stpes {
				withMaven(maven : 'maven_3.5.4'){
					sh 'mvn clean install'
				  }
			}	
		}
		stage ( 'Testing Stage') {
			stpes {
				withMaven(maven : 'maven_3.5.4'){
					sh 'mvn test'
				  }
			}	
		}
		stage ( 'Deployment Stage') {
			stpes {
				withMaven(maven : 'maven_3.5.4'){
					sh 'mvn deploy'
				  }
			}	
		}
				
	}
}