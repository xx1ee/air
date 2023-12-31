package com.xx1ee.entity;

import com.xx1ee.classes.AirportName;
import com.xx1ee.classes.City;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geolatte.geom.Geometry;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Point;

import java.util.List;
import java.util.Set;

@NamedEntityGraph(name = "withDepartureAndArrival",
        attributeNodes = {
            @NamedAttributeNode(value = "departureList", subgraph = "departure-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "departure-subgraph",
                        attributeNodes = {
                                @NamedAttributeNode("departure_airport"),
                                @NamedAttributeNode("arrival_airport"),
                                @NamedAttributeNode("aircraft_code")
                        }
                )
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "airports_data", schema = "bookings")
@Entity
public class airports_data implements BaseEntity{
    @Id
    String airport_code;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    AirportName airport_name;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    City city;
    //@Column(name = "coordinates",columnDefinition="Point")
    Point coordinates;
    String timezone;
    @OneToMany(mappedBy="departure_airport")
    List<flights> departureList;
    @OneToMany(mappedBy = "arrival_airport")
    Set<flights> arrivalList;
}
