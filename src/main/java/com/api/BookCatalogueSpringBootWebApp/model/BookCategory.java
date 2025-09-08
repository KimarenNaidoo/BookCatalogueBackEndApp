package com.api.BookCatalogueSpringBootWebApp.model;

public enum BookCategory {

    SELFIMPROVEMENT_PSYCHOLOGY(0, "Self-Improvement/ Psychology"),
    SOFTWARE_TECHNOLOGY(1, "Software Engineering/ Technology"),
    ENGINEERING_PHYSICS(2, "Engineering/ Physics"),
    FICTION(3, "Fiction"),
    ART(4, "Art"),
    ROMANCE(5, "Romance"),
    HISTORY(6, "History"),
    MYSTERY_SPY_DETECTIVE(7, "Mystery/ Spy/ Detective");

    private int categoryId;
    private String categoryDisplayValue;

    BookCategory(int categoryId, String categoryDisplayValue) {
        this.categoryId = categoryId;
        this.categoryDisplayValue = categoryDisplayValue;
    }

    public int getCategoryId() { return categoryId; }

    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getCategoryDisplayValue() { return categoryDisplayValue; }

    public void setCategoryDisplayValue(String categoryDisplayValue) { this.categoryDisplayValue = categoryDisplayValue; }

    public static BookCategory getBookCategory(int categoryId) {
        for (BookCategory bookCategory : BookCategory.values()) {
            if (bookCategory.getCategoryId() == categoryId)
                return bookCategory;
        }

        return null;
    }
    
}
