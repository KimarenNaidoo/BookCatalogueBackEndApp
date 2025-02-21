package com.example.BookCatalogueSpringBootWebApp.model;

import java.util.Objects;

public class Book implements Comparable<Book> {

    private long id;
    private String title;
    private int statusId; // Investigate enums
    private int categoryId; 
    private String author; // Text seperated by , for multiple authors
    private int ownershipId;

    // Status
    private final static int NOT_STARTED = 0;
    private final static int READING = 1;
    private final static int FINISHED = 2;

    // Category
    private final static int SELFIMPROVEMENT_PSYCHOLOGY = 0;
    private final static int SOFTWARE_TECHNOLOGY = 1;
    private final static int ENGINEERING_PHYSICS = 2;
    private final static int FICTION = 3;
    private final static int ART = 4;
    private final static int ROMANCE = 5;
    private final static int HISTORY = 6;
    private final static int MYSTERY_SPY_DETECTIVE = 7;

    // Ownership
    private final static int UNOWN = 0;
    private final static int LENT = 1;
    private final static int BORROWED = 2;
    private final static int OWN = 3; 

    public Book(){}

    public Book(long id, String title, int statusId, int categoryId, String author, int ownershipId) {
        this.id = id; // Make this auto-incrememnt
        this.title = title;
        this.statusId = statusId;
        this.categoryId = categoryId;
        this.author = author;
        this.ownershipId = ownershipId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getOwnershipId() {
        return ownershipId;
    }

    public void setOwnershipId(int ownershipId) {
        this.ownershipId = ownershipId;
    }


    public String getOwnership() {
        switch (this.ownershipId) {
            case UNOWN:
                return "Unown";

            case LENT:
                return "Lent To...";    

            case BORROWED:
                return "Borrowed From...";

            case OWN:
                return "Own";

            default:
                return "N/A";
        }
    }

    public String getCategory() {
        switch (this.categoryId) {
            case SELFIMPROVEMENT_PSYCHOLOGY:
                return "Self-Improvement/Psychology";

            case SOFTWARE_TECHNOLOGY:
                return "Software Engineering/Technolgy";

            case ENGINEERING_PHYSICS:
                return "Engineering/Physics";

            case FICTION:
                return "Fiction";

            case ART:
                return "Art";

            case ROMANCE:
                return "Romance";

            case HISTORY:
                return "History";

            case MYSTERY_SPY_DETECTIVE:
                return "Mystery/Spy/Detective";
        
            default:
                return "N/A";
        }
    }

    public String getStatus() {
        switch (this.statusId) {
            case NOT_STARTED:
                return "Not Started";
            
            case READING:
                return "Reading";

            case FINISHED:
                return "Finished";
        
            default:
                return "N/A";
        }
    }

    public String displayBookString() {
		return String.format("<h3>Book Id: %d</h3> <ul><li>Title: %s.</li> <li>Author: %s.</li> <li>Category: %s.</li> <li>Status: %s.</li> <li>Ownership: %s.</li></ul>", this.getId(), this.getTitle(), this.getAuthor(), this.getCategory(), this.getStatus(), this.getOwnership());
	}

    @Override
    public int compareTo(Book o) {
        return 1;// update after implementing ISBN
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Book book = (Book) obj;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title);
    }
}