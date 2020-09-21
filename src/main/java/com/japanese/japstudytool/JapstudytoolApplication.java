package com.japanese.japstudytool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class JapstudytoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(JapstudytoolApplication.class, args);
	}

}
