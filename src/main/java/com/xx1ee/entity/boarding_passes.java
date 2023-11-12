package com.xx1ee.entity;

import com.xx1ee.classes.BoardingPassesPK;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class boarding_passes implements BaseEntity{
    @EmbeddedId
    BoardingPassesPK boardingPassesPK;
    @Column(name = "boarding_no")
    Integer boarding_no;
    @Column(name="seat_no")
    String seat_no;
    @OneToOne
    @JoinColumns({@JoinColumn(name = "ticket_no"), @JoinColumn(name = "flight_id")})
    ticket_flights ticket_flights;
}
