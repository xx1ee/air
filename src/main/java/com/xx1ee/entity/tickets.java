package com.xx1ee.entity;

import com.xx1ee.classes.ContactData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;
@NamedEntityGraph(name = "withFlightsList",
        attributeNodes = {
                @NamedAttributeNode("flightsList")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tickets", schema = "bookings")
@Entity
public class tickets implements BaseEntity{
    @Id
    String ticket_no;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_ref")
    bookings book_ref;
    String passenger_id;
    String passenger_name;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    ContactData contact_data;
    @OneToMany(mappedBy = "ticketFlightsId.tickets", fetch = FetchType.LAZY)
    List<ticket_flights> flightsList;
}
