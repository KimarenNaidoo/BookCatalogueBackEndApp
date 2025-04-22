package com.example.BookCatalogueSpringBootWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.BookCatalogueSpringBootWebApp.model")
@EnableJpaRepositories("com.example.BookCatalogueSpringBootWebApp.repository")
public class BookCatalogueSpringBootWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogueSpringBootWebAppApplication.class, args);
	}

}