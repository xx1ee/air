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
    AirportName airport_name;
    @JdbcTypeCode(SqlTypes.JSON)
    City city;
    @ManyToOne
    @JoinColumn(nullable = false, name = "")
    flights flights;
    String coordinates;
    String timezone;
}
