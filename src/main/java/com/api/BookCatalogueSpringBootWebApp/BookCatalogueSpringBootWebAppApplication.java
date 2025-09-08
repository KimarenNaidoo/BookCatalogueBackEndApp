package com.api.BookCatalogueSpringBootWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.api.BookCatalogueSpringBootWebApp.model")
@EnableJpaRepositories("com.api.BookCatalogueSpringBootWebApp.repository")
public class BookCatalogueSpringBootWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogueSpringBootWebAppApplication.class, args);
	}

}