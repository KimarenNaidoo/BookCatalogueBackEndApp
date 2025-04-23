package com.example.BookCatalogueSpringBootWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EntityScan("com.example.BookCatalogueSpringBootWebApp.model")
@EnableJpaRepositories("com.example.BookCatalogueSpringBootWebApp.repository")
public class BookCatalogueSpringBootWebAppApplication {

	public static void main(String[] args) {
		setEnvironmentalVariables();	
		SpringApplication.run(BookCatalogueSpringBootWebAppApplication.class, args);
	}


	private static void setEnvironmentalVariables() {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		String dbUrl = dotenv.get("DATABASE_URL", dotenv.get("DATABASE_URL"));
		String dbUsername = dotenv.get("DATABASE_USERNAME", dotenv.get("DATABASE_USERNAME"));
		String dbPassword = dotenv.get("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));

		System.setProperty("DATABASE_URL", dbUrl);
		System.setProperty("DATABASE_USERNAME", dbUsername);
		System.setProperty("DATABASE_PASSWORD", dbPassword);
	}

}