package com.api.BookCatalogueSpringBootWebApp.model;

public enum BookStatus {
    
    NOT_STARTED(0, "Not Started"),
    READING(1, "Reading"),
    FINISHED(2, "Finished");

    private int statusId;
    private String statusDisplayValue;

    BookStatus(int statusId, String statusDisplayValue) {
        this.statusId = statusId;
        this.statusDisplayValue = statusDisplayValue;
    }

    public int getStatusId() { return statusId; }

    public void setStatusId(int statusId) { this.statusId = statusId; }

    public String getStatusDisplayValue() { return statusDisplayValue; }

    public void setStatusDisplayValue(String statusDisplayValue) { this.statusDisplayValue = statusDisplayValue; }

    public static BookStatus getBookStatus(int statusId) {
        for (BookStatus bookStatus : BookStatus.values()) {
            if (bookStatus.getStatusId() == statusId)
                return bookStatus;
        }

        return null;
    }
}
