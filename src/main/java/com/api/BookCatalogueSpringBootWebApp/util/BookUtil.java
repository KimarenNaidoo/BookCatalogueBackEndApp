package com.api.BookCatalogueSpringBootWebApp.util;

import java.time.format.DateTimeFormatter;

import com.api.BookCatalogueSpringBootWebApp.model.Book;
import com.api.BookCatalogueSpringBootWebApp.model.BookCategory;
import com.api.BookCatalogueSpringBootWebApp.model.BookOwnership;
import com.api.BookCatalogueSpringBootWebApp.model.BookStatus;

public class BookUtil {

    Book book;

    public BookUtil(Book book) {
        this.book = book;
    }

    public String displayBookString() {
        return String.format("<h3>Book Id: %d</h3> <ul><li>Title: %s.</li> <li>Author: %s.</li> <li>Category: %s.</li> <li>Status: %s.</li> <li>Ownership: %s.</li> <li>ISBN: %s.</li> <li>Create Date: %s.</li></ul>", book.getId(), book.getTitle(), book.getAuthor(), this.getCategory(), this.getStatus(), this.getOwnership(), book.getIsbn(), this.getFormattedCreateDate());
    }

    public String getCategory() {
        BookCategory bookCategory = BookCategory.getBookCategory(book.getCategoryId());
        return bookCategory != null ? bookCategory.getCategoryDisplayValue() : "N/A";
    }

    public String getStatus() {
        BookStatus bookStatus = BookStatus.getBookStatus(book.getStatusId());
        return bookStatus != null ? bookStatus.getStatusDisplayValue() : "N/A";
    }

    public String getOwnership() {
        BookOwnership bookOwnership = BookOwnership.getBookOwnership(book.getOwnershipId());
        return bookOwnership != null ? bookOwnership.getOwnershipDisplayValue() : "N/A";
    }

    public String getFormattedCreateDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return book.getCreateDate().format(dateTimeFormatter);
    }
    
}
