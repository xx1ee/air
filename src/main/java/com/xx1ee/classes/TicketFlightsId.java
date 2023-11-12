package com.xx1ee.classes;

import com.xx1ee.entity.flights;
import com.xx1ee.entity.tickets;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    @JoinColumn(name = "ticket_no")
    private tickets tickets;
    @ManyToOne
    @JoinColumn(name = "flight_id")
    private flights flights;
}
