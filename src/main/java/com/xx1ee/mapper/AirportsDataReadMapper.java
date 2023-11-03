package com.xx1ee.mapper;

import com.xx1ee.dto.AirportsDataReadDto;
import com.xx1ee.entity.airports_data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

public class AirportsDataReadMapper implements Mapper<airports_data, AirportsDataReadDto>{
    @Override
    public AirportsDataReadDto mapFrom(airports_data object) {
        return new AirportsDataReadDto(object.getAirport_code(), object.getAirport_name(), object.getCity(),
                object.getCoordinates(), object.getTimezone());
    }
}
