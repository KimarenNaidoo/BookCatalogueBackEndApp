package com.example.BookCatalogueSpringBootWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.BookCatalogueSpringBootWebApp.model.Book;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    
}
