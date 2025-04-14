package com.example.BookCatalogueSpringBootWebApp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.BookCatalogueSpringBootWebApp.service.BookService;
import com.example.BookCatalogueSpringBootWebApp.controller.BookController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(BookController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class BookControllerTest {

    private final String apiEndpoint = "/api/books/";
    private final String getBooksEndpointResponseString = "{\"title\":\"The Alchemist\",\"status\":\"Finished\",\"category\":\"Fiction\",\"author\":\"Paulo Coelho\",\"ownership\":\"Unown\",\"isbn\":\"978-0-14-310565-5\"}";

    @Autowired
    private MockMvc mockMvc;

    BookService mockService = Mockito.mock(BookService.class);

    @Test
    public void testGetAllBooksEndpoint() throws Exception {
        mockMvc.perform(get(apiEndpoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(getBooksEndpointResponseString)));
    }

    @Test 
    public void testGetBookByIdEndpoint() throws Exception {
        mockMvc.perform(get(apiEndpoint + "{id}", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(getBooksEndpointResponseString));
    }

}
