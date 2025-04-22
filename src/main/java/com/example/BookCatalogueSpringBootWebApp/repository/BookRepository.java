package com.example.BookCatalogueSpringBootWebApp.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import com.example.BookCatalogueSpringBootWebApp.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookRepository {

    private final String JSON_RESOURCE = "booksData.json";
    private final ObjectMapper objectMapper;

    public BookRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public List<Book> loadBooksFromRepository() {
        return loadBooksFromJsonResource();
    }

    private List<Book> loadBooksFromJsonResource() {
        List<Book> books;

        try {
            ClassPathResource resource = new ClassPathResource(JSON_RESOURCE);
            String jsonContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            Map<String, List<Book>> booksMap = objectMapper.readValue(jsonContent, new TypeReference<Map<String, List<Book>>>() {}
            );      
            
            books = booksMap.get("books");
        }
        catch (IOException e) {
            books = new ArrayList<>();
            e.printStackTrace();
        }

        return books;
    }
}
