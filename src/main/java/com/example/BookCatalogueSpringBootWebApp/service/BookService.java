package com.example.BookCatalogueSpringBootWebApp.service;

import org.springframework.stereotype.Service;
import com.example.BookCatalogueSpringBootWebApp.model.Book;
import com.example.BookCatalogueSpringBootWebApp.repository.BookRepository;
import java.util.List;
import java.util.Optional;  

@Service
public class BookService {

    private List<Book> books;
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository; 
    }

    public List<Book> getAllBooks() {
        books = bookRepository.findAll();
        return books;
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    
}
