package com.xx1ee.classes;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketFlightsId implements Serializable {
    public String ticket_no;
    public Integer flight_id;
}
