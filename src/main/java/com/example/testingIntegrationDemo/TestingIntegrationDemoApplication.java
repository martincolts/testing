package com.example.testingIntegrationDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class TestingIntegrationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingIntegrationDemoApplication.class, args);
	}
	
	@Configuration
	public class Config {
		
	}
}
