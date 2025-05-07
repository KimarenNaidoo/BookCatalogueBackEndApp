package com.api.BookCatalogueSpringBootWebApp.dto;

import com.api.BookCatalogueSpringBootWebApp.model.Book;
import com.api.BookCatalogueSpringBootWebApp.util.BookUtil;

public class BookDTO {
    
    private String title;
    private String status;
    private String category;
    private String author;
    private String ownership;
    private String isbn;  

    public BookDTO(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public static BookDTO fromEntity(Book book) {
        BookDTO dto = new BookDTO();
        BookUtil bookUtil = new BookUtil(book);
        dto.setTitle(book.getTitle());
        dto.setStatus(bookUtil.getStatus());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setOwnership(bookUtil.getOwnership());
        dto.setCategory(bookUtil.getCategory());

        return dto;
    }
}
