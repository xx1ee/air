package com.xx1ee.classes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardingPassesPK implements Serializable {
    public String ticket_no;
    public Integer flight_id;
}
