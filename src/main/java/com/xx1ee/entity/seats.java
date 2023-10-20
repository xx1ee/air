package com.xx1ee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "seats", schema = "bookings")
public class seats {
    @Id
    String aircraft_code;
    String seat_no;
    String fare_conditions;
}
