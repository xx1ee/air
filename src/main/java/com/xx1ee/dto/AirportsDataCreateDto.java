package com.xx1ee.dto;

import com.xx1ee.classes.AirportName;
import com.xx1ee.classes.City;
import org.locationtech.jts.geom.Point;

public record AirportsDataCreateDto(String airports_code,
                                  AirportName airportName,
                                  City city,
                                  Point coordinates,
                                  String timezone) {
}
