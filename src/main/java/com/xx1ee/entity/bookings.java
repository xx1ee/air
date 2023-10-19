package com.xx1ee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    LocalDateTime book_date;
    Long total_amount;
    @OneToMany
    List<tickets> tickets = new ArrayList<>();
}