package com.xx1ee.service;

import com.xx1ee.classes.SeatsPK;
import com.xx1ee.entity.seats;
import com.xx1ee.entity.tickets;
import com.xx1ee.repos.SeatsRepository;
import com.xx1ee.repos.TicketsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SeatsService {
    private final SeatsRepository seatsRepository;
    public SeatsPK create(seats seats) {
        return seatsRepository.save(seats).getSeatsPK();
    }
    @Transactional
    public boolean delete(SeatsPK id) {
        var maybeAircraft = seatsRepository.findById(id);
        maybeAircraft.ifPresent(aircraft -> seatsRepository.delete(id));
        return maybeAircraft.isPresent();
    }
    public Optional<seats> findById(SeatsPK id) {
        return seatsRepository.findById(id);
    }
    public List<seats> findAll() {
        return seatsRepository.findAll();
    }
    public void update(seats seats) {
        seatsRepository.update(seats);
    }
}
