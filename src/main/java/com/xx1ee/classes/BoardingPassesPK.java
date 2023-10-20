package com.xx1ee.classes;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BoardingPassesPK implements Serializable {
    String ticket_no;
    Integer flight_id;
}
