package com.xx1ee.entity;

import com.xx1ee.classes.BoardingPassesPK;
import com.xx1ee.classes.TicketFlightsId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ticket_flights implements BaseEntity{
    @EmbeddedId
    TicketFlightsId ticketFlightsId;
    String fare_conditions;
    Long amount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ticket_no", updatable = false, insertable = false)
    tickets tickets;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="flight_id", updatable = false, insertable = false)
    flights flights;
}
