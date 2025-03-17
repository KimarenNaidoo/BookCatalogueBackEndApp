package com.example.BookCatalogueSpringBootWebApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookCatalogueSpringBootWebApp.service.BookService;
import com.example.BookCatalogueSpringBootWebApp.util.BookUtil;
import com.example.BookCatalogueSpringBootWebApp.model.Book;
import java.util.List;


@RestController
@RequestMapping("/api/books")
public class BookController {

	public BookService bookService = new BookService();

	@GetMapping("/")
    public List<Book> getAllBooks() {
        List<Book> books = bookService.getAllBooks();

		return books;
    }

	@GetMapping("/{id}")
	public Book getBookById(@PathVariable("id") Long id) {
		Book book = null;

		try {
			book = bookService.getBookById(id).get();
		} catch(Exception e) {
			System.err.println("Error: Unable to resolve Book entry with Id: " + id);
	
		}
		
		return book;
	}

}
