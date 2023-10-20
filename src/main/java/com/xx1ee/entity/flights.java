package com.xx1ee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "flights", schema = "bookings")
@Entity
public class flights {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flights_flight_id_seq")
    @Column(name = "flight_id", updatable = false, nullable = false)
    Integer flight_id;
    @Column(nullable = false)
    String flight_no;
    @Column(nullable = false)
    OffsetDateTime scheduled_departure;
    @Column(nullable = false)
    OffsetDateTime getScheduled_arrival;
    @ManyToOne
    @Column(nullable = false)
    @JoinColumn(name = "departure_airport")
    airports_data departure_airport;
    @ManyToOne
    @Column(nullable = false)
    @JoinColumn(name = "arrival_airport")
    airports_data arrival_airport;
    @Column(nullable = false)
    String status;
    @ManyToOne
    @JoinColumn(name = "aircraft_code")
    @Column(nullable = false)
    aircrafts_data aircraft_code;
    OffsetDateTime actual_departure;
    OffsetDateTime actual_arrival;
    @OneToMany(mappedBy = "flights")
    List<ticket_flights> ticketsList = new ArrayList<>();
}
