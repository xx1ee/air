package com.xx1ee.entity;

import com.xx1ee.classes.ContactData;
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
@Table(name = "tickets", schema = "bookings")
@Entity
public class tickets {
    @Id
    String ticket_no;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_ref")
    bookings book_ref;
    @ManyToOne(fetch = FetchType.LAZY)
    flights flights;
    String passenger_id;
    String passenger_name;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    ContactData contact_data;
}
