package com.xx1ee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "flights", schema = "bookings")
@Entity
public class flights {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flights_flight_id_seq")
    @SequenceGenerator(name = "flights_flight_id_seq", sequenceName = "flight_seq", allocationSize = 1)
    @Column(name = "flight_id", updatable = false, nullable = false)
    Integer flight_id;
    @Column(name = "flight_no", nullable = false)
    String flight_no;
    @Column(name="scheduled_departure", nullable = false)
    OffsetDateTime scheduled_departure;
    @Column(name="scheduled_arrival", nullable = false)
    OffsetDateTime scheduled_arrival;
    @ManyToOne
    @JoinColumn(name = "departure_airport", nullable = false, insertable = false, updatable = false)
    airports_data departure_airport;
    @ManyToOne
    @JoinColumn(name = "arrival_airport", nullable = false, insertable = false, updatable = false)
    airports_data arrival_airport;
    @Column(name = "status", nullable = false)
    String status;
    @ManyToOne
    @JoinColumn(name = "aircraft_code", nullable = false, insertable = false, updatable = false)
    aircrafts_data aircraft_code;
    OffsetDateTime actual_departure;
    OffsetDateTime actual_arrival;
    @OneToMany(mappedBy = "flights")
    List<ticket_flights> ticketsList = new ArrayList<>();
}
