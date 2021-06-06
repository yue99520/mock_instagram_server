package com.ernie.mock_instagram_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class MockInstagramServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockInstagramServerApplication.class, args);
	}
}
