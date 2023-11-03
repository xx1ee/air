package com.xx1ee.service;

import com.xx1ee.dto.AirportsDataReadDto;
import com.xx1ee.entity.airports_data;
import com.xx1ee.mapper.AirportsDataReadMapper;
import com.xx1ee.repos.AirportsDataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AirportsDataService {
    private final AirportsDataRepository airportsDataRepository;
    private final AirportsDataReadMapper airportsDataReadMapper;
    @Transactional
    public boolean delete(String id) {
        var maybeAircraft = airportsDataRepository.findById(id);
        maybeAircraft.ifPresent(aircraft -> airportsDataRepository.delete(id));
        return maybeAircraft.isPresent();
    }

    public Optional<AirportsDataReadDto> findById(String id) {
        return airportsDataRepository.findById(id).map(airportsDataReadMapper::mapFrom);
    }

    public void update(airports_data airports_data) {
        airportsDataRepository.update(airports_data);
    }
    public void save(airports_data airports_data) {
        airportsDataRepository.save(airports_data);
    }
}
