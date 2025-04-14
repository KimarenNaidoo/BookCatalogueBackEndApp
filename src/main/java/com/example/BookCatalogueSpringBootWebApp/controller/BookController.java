package com.example.BookCatalogueSpringBootWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookCatalogueSpringBootWebApp.service.BookService;
import com.example.BookCatalogueSpringBootWebApp.dto.BookDTO;
import com.example.BookCatalogueSpringBootWebApp.model.Book;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/books")
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/")
    public List<BookDTO> getAllBooks() {
		return bookService.getAllBooks().stream().map(BookDTO::fromEntity).collect(Collectors.toList());
    }

	@GetMapping("/{id}")
	public BookDTO getBookById(@PathVariable("id") Long id) {
		Book book = null;
		BookDTO bookDTO = null;

		try {
			book = bookService.getBookById(id).get();
			bookDTO = BookDTO.fromEntity(book);
		} catch(Exception e) {
			System.err.println("Error: Unable to resolve Book entry with Id: " + id);
	
		}
		
		return bookDTO;
	}

}
