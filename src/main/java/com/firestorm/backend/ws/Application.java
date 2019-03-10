package com.firestorm.backend.ws;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	final static String ALLOWED_ORIGINS = "http://int-ws.gestion-des-acces.fr/firestorm";
	//final static String ALLOWED_ORIGINS = "http://localhost:8080/firestorm";
	//final static String ALLOWED_ORIGINS_VSCODE = "http://localhost:4200";
	//final static String ALLOWED_ORIGINS = "http://localhost:4200";
	
	  @Override
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	    return application.sources(Application.class);
	  }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/api/**")
                        //.allowedOrigins("http://localhost:4200")
                         //.allowedOrigins("http://localhost:8080/firestorm")
						.allowedOrigins(ALLOWED_ORIGINS)
						//.allowedOrigins(ALLOWED_ORIGINS_VSCODE)
                        .allowedMethods("*");
            }
        };
    }
}

@RestController
class FileResource {


	//final static String COMMMENTS_FILES = "/MIDDLE/firestorm/firestorm-comments/comments.txt";
	final static String CONTACT_MESSAGE_FILE_NAME = "firestorm_messages.txt";
	//final static String CONTACT_MESSAGE_FILES_PATH = "D:/projects/firestorm-int/comments/" + CONTACT_MESSAGE_FILE_NAME;
	final static String CONTACT_MESSAGE_FILES_PATH = "/MIDDLE/firestorm/firestorm-comments/" + CONTACT_MESSAGE_FILE_NAME;
	
	
	@GetMapping(path = "/api/files", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getFile() {
        String exportedContent = "Hello, World!";
        String filename = "my-file.txt";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlExposeHeaders(Collections.singletonList("Content-Disposition"));
        headers.set("Content-Disposition", "attachment; filename=" + filename);
        return new ResponseEntity<>(exportedContent, headers, HttpStatus.OK);
    }
    
    @GetMapping(path = "/api/downloadcomments", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getCommentsFile() {
         HttpHeaders headers = new HttpHeaders();
        String exportedContent = getFileContents(CONTACT_MESSAGE_FILES_PATH);
        headers.setAccessControlExposeHeaders(Collections.singletonList("Content-Disposition"));
        headers.set("Content-Disposition", "attachment; filename=" + CONTACT_MESSAGE_FILE_NAME);
        return new ResponseEntity<>(exportedContent, headers, HttpStatus.OK);
    }
    
	@GetMapping(path = "/api/storemessage/message/{message}/fuser/{fuser}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> storeComment(@PathVariable String message,@PathVariable String fuser) {
		try {
		FileWriter fw = new FileWriter(CONTACT_MESSAGE_FILES_PATH,true); //the true will append the new data
		fw.write("\n\n==================== Message de "+ fuser +" du " +  new Date().toString() + "======================\n");//appends the string to the file
		fw.write( message);
		fw.write("\n");
		fw.close();
		
		System.out.println("Message écrit avec succès");
		return new ResponseEntity<String>(HttpStatus.OK);
		}catch(Exception e){
			System.out.println(" Erreur lors de la sauvegarde du mesage: " + e);		
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}	
    }
	
	private String getFileContents(String fileName) {
		
		String exportedContent = "Not data found";
		try {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		StringBuilder stringBuilder = new StringBuilder();
		String line = null;
		String ls = System.getProperty("line.separator");
		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		// delete the last new line separator
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		reader.close();

		 exportedContent = stringBuilder.toString();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return exportedContent;
	}
}
