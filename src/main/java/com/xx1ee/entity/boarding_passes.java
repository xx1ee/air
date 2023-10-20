package com.xx1ee.entity;

import com.xx1ee.classes.BoardingPassesPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class boarding_passes {
    @EmbeddedId
    BoardingPassesPK boardingPassesPK;
    Integer boarding_no;
    String seat_no;
    @OneToOne
    @JoinColumns({@JoinColumn(name = "ticket_no"), @JoinColumn(name = "flight_id")})
    ticket_flights ticket_flights;
}
