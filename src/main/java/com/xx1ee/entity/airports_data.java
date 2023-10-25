package com.xx1ee.entity;

import com.xx1ee.classes.AirportName;
import com.xx1ee.classes.City;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "airports_data", schema = "bookings")
@Entity
public class airports_data {
    @Id
    String airport_code;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    AirportName airport_name;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    City city;
    String coordinates;
    String timezone;
    @OneToMany(mappedBy="departure_airport")
    List<flights> departureList;
    @OneToMany(mappedBy = "arrival_airport")
    List<flights> arrivalList;
}
