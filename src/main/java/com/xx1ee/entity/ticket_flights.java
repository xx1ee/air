package com.xx1ee.entity;

import com.xx1ee.classes.BoardingPassesPK;
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
public class ticket_flights {
    @EmbeddedId
    BoardingPassesPK boardingPassesPK;
    String fare_conditions;
    Long amount;
    @OneToMany
    @JoinColumn(name="ticket_no")
    List<tickets> tickets;
    @ManyToOne
    @JoinColumn(name="flight_id")
    flights flights;
}
