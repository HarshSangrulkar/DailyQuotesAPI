package com.quotes.quotes;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuotesApplication {

	public static void main(String[] args) {
		// Load environment variables from .env file
		Dotenv dotenv = Dotenv.load();
		System.setProperty("GREETING_API_URL", dotenv.get("GREETING_API_URL"));
		System.setProperty("API_KEY", dotenv.get("API_KEY"));

		SpringApplication.run(QuotesApplication.class, args);
	}
}
