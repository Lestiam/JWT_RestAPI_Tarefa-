package com.example.JWT_RestAPI3.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.example"})
@EnableMongoRepositories("com.example.JWT_RestAPI3.repository")
public class JwtRestApi3Application {

	public static void main(String[] args) {
		SpringApplication.run(JwtRestApi3Application.class, args);
	}

}
