package com.xx1ee.classes;

public enum Status{
    ON_TIME("On Time"),
    DELAYED("Delayed"),
    DEPARTED("Departed"),
    ARRIVED("Arrived"),
    SCHEDULED("Scheduled"),
    CANCELLED("Cancelled");

    private String status;

    Status(String status) {
        this.status = status;
    }

}
