package com.xx1ee.service;

import com.xx1ee.dto.AirportsDataCreateDto;
import com.xx1ee.dto.AirportsDataReadDto;
import com.xx1ee.entity.aircrafts_data;
import com.xx1ee.entity.airports_data;
import com.xx1ee.entity.flights;
import com.xx1ee.entity.ticket_flights;
import com.xx1ee.mapper.AirportsDataCreateMapper;
import com.xx1ee.mapper.AirportsDataReadMapper;
import com.xx1ee.repos.AirportsDataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class AirportsDataService {
    private final AirportsDataRepository airportsDataRepository;
    private final AirportsDataReadMapper airportsDataReadMapper;
    private final AirportsDataCreateMapper airportsDataCreateMapper;
    @Transactional
    public boolean delete(String id) {
        var maybeAircraft = airportsDataRepository.findById(id);
        maybeAircraft.ifPresent(aircraft -> airportsDataRepository.delete(maybeAircraft.get()));
        return maybeAircraft.isPresent();
    }

    public Optional<AirportsDataReadDto> findById(String id) {
        return airportsDataRepository.findById(id).map(airportsDataReadMapper::mapFrom);
    }

    public void update(airports_data airports_data) {
        airportsDataRepository.update(airports_data);
    }
    public void save(AirportsDataCreateDto airports_data) {
        airportsDataRepository.save(airportsDataCreateMapper.mapFrom(airports_data));
    }
    public List<airports_data> findAll() {
        return airportsDataRepository.findAll();
    }
    @Transactional
    public List<flights> getDepartures(String id) {
        var maybeAircraft = airportsDataRepository.findById(id);
        if (maybeAircraft.isPresent()) {
            return maybeAircraft.get().getDepartureList();
        }
        return new ArrayList<>();
    }
    @Transactional
    public Set<flights> getArrivals(String id) {
        var maybeAircraft = airportsDataRepository.findById(id);
        if (maybeAircraft.isPresent()) {
            return maybeAircraft.get().getArrivalList();
        }
        return Set.of(new flights());
    }

}
