package com.xx1ee.classes;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeatsPK implements Serializable {
    String aircraft_code;
    String seat_no;
}
