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
public class BoardingPassesPK implements Serializable {
    String ticket_no;
    Integer flight_id;
}
