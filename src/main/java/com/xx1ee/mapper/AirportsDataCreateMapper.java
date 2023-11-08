package com.xx1ee.mapper;

import com.xx1ee.dto.AirportsDataCreateDto;
import com.xx1ee.dto.AirportsDataReadDto;
import com.xx1ee.entity.airports_data;

public class AirportsDataCreateMapper implements Mapper<AirportsDataCreateDto, airports_data>{
    @Override
    public airports_data mapFrom(AirportsDataCreateDto object) {
        return airports_data.builder()
                .airport_name(object.airportName())
                .airport_code(object.airports_code())
                .city(object.city())
                .timezone(object.timezone())
                .build();
    }
}
