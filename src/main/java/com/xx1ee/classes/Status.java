package com.xx1ee.classes;

public enum Status{
    On_Time("On Time"),
    Delayed("Delayed"),
    Departed("Departed"),
    Arrived("Arrived"),
    Scheduled("Scheduled"),
    Cancelled("Cancelled");

    private String status;

    Status(String status) {
        this.status = status;
    }

}
