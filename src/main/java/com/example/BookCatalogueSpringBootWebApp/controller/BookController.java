package com.example.BookCatalogueSpringBootWebApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookCatalogueSpringBootWebApp.service.BookService;
import com.example.BookCatalogueSpringBootWebApp.util.APIUtil;
import com.example.BookCatalogueSpringBootWebApp.dto.BookDTO;
import com.example.BookCatalogueSpringBootWebApp.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/books")
public class BookController {

	private final BookService bookService;
	private final APIUtil apiComponent;

	public BookController(BookService bookService) {
		this.bookService = bookService;
		this.apiComponent = new APIUtil("1");
	}

	@GetMapping("/")
    public List<BookDTO> getAllBooks() {
		List<BookDTO> bookDTOs = new ArrayList();

		try {
			apiComponent.logApiCall("getAllBooks");
			bookDTOs = bookService.getAllBooks().stream().map(BookDTO::fromEntity).collect(Collectors.toList());
		}
		catch (Exception e) {
			System.err.println("Error: Unable to retrieve book entries");
		}

		return bookDTOs;
    }

	@GetMapping("/{id}")
	public BookDTO getBookById(@PathVariable("id") Long id) {
		Book book = null;
		BookDTO bookDTO = null;

		try {
			apiComponent.logApiCall("getBookById");
			book = bookService.getBookById(id).get();
			bookDTO = BookDTO.fromEntity(book);
		} catch(Exception e) {
			System.err.println("Error: Unable to resolve Book entry with Id: " + id);
		}
		
		return bookDTO;
	}

}
