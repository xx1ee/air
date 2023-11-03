package com.xx1ee.service;

import com.xx1ee.entity.BaseEntity;
import com.xx1ee.entity.aircrafts_data;
import com.xx1ee.repos.AircraftsDataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AircraftsDataService {
    private final AircraftsDataRepository aircraftsDataRepository;
    public String create(aircrafts_data aircrafts_data) {
        return aircraftsDataRepository.save(aircrafts_data).getAircraft_code();
    }
    @Transactional
    public boolean delete(String id) {
        var maybeAircraft = aircraftsDataRepository.findById(id);
        maybeAircraft.ifPresent(aircraft -> aircraftsDataRepository.delete(id));
        return maybeAircraft.isPresent();
    }
    public Optional<aircrafts_data> findById(String id) {
        return aircraftsDataRepository.findById(id);
    }
    public List<aircrafts_data> findAll() {
        return aircraftsDataRepository.findAll();
    }
    public void update(aircrafts_data aircrafts_data) {
        aircraftsDataRepository.update(aircrafts_data);
    }
}
