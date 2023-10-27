package com.xx1ee.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bookings", schema = "bookings")
@Entity
public class bookings {
    @Id
    String book_ref;
    OffsetDateTime book_date;
    Long total_amount;
    @OneToMany(mappedBy = "book_ref", fetch = FetchType.EAGER)
    @Column(insertable = false, updatable = false)
    List<tickets> tickets = new ArrayList<>();
}
