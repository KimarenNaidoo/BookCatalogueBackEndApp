package com.api.BookCatalogueSpringBootWebApp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import com.api.BookCatalogueSpringBootWebApp.model.Book;

public interface BookRepository extends IBookCatalogueRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.id > ?1 ORDER BY b.id ASC LIMIT ?2")
    List<Book> findBooksWithPagination(int offset, int limit);
    
}
