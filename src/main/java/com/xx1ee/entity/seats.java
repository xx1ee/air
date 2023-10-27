package com.xx1ee.entity;

import com.xx1ee.classes.BoardingPassesPK;
import com.xx1ee.classes.SeatsPK;
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
@Table(name = "seats", schema = "bookings")
public class seats implements BaseEntity{
    @EmbeddedId
    SeatsPK seatsPK;
    @ManyToOne
    @JoinColumn(name = "aircraft_code", insertable = false, updatable = false)
    aircrafts_data aircraft_code;
    String fare_conditions;
}
