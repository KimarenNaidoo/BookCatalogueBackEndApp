package com.example.BookCatalogueSpringBootWebApp.util;

public class APIUtil {

    private String apiVersion;

    public APIUtil(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void logApiCall(String endpoint) {
        System.out.println("API v" + apiVersion + " call to: " + endpoint);
    }
    
}
