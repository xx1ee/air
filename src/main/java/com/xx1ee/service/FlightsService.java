package com.xx1ee.service;

import com.xx1ee.classes.SeatsPK;
import com.xx1ee.entity.flights;
import com.xx1ee.entity.seats;
import com.xx1ee.repos.FlightsRepository;
import com.xx1ee.repos.SeatsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
public class FlightsService {
    private final FlightsRepository flightsRepository;
    public Integer create(flights flights) {
        return flightsRepository.save(flights).getFlight_id();
    }
    @Transactional
    public boolean delete(Integer id) {
        var maybeAircraft = flightsRepository.findById(id);
        maybeAircraft.ifPresent(aircraft -> flightsRepository.delete(maybeAircraft.get()));
        return maybeAircraft.isPresent();
    }
    public Optional<flights> findById(Integer id) {
        return flightsRepository.findById(id);
    }
    public List<flights> findAll() {
        return flightsRepository.findAll();
    }
    public void update(flights flights) {
        flightsRepository.update(flights);
    }
}
