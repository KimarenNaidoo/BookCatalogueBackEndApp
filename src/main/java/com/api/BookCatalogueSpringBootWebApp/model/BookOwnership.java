package com.api.BookCatalogueSpringBootWebApp.model;

public enum BookOwnership {
    
    UNOWN(0, "Unown"),
    LENT(1, "Lent To..."),
    BORROWED(2, "Borrowed From..."),
    OWN(3, "Own");

    private int ownershipId;
    private String ownershipDisplayValue; 

    BookOwnership(int ownershipId, String ownershipDisplayValue) {
        this.ownershipId = ownershipId;
        this.ownershipDisplayValue = ownershipDisplayValue;
    }

    public int getOwnershipId() { return ownershipId; }

    public void setOwnershipId(int ownershipId) { this.ownershipId = ownershipId; }

    public String getOwnershipDisplayValue() { return ownershipDisplayValue; }

    public void setOwnershipDisplayValue(String ownershipDisplayValue) { this.ownershipDisplayValue = ownershipDisplayValue; }

    public static BookOwnership getBookOwnership(int ownershipId) {
        for (BookOwnership bookOwnership : BookOwnership.values()) {
            if (bookOwnership.getOwnershipId() == ownershipId)
                return bookOwnership;
        }

        return null;
    }
}
