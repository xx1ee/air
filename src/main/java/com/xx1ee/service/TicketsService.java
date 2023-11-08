package com.xx1ee.service;

import com.xx1ee.entity.aircrafts_data;
import com.xx1ee.entity.tickets;
import com.xx1ee.repos.TicketsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TicketsService {
    private final TicketsRepository ticketsRepository;
    public String create(tickets tickets) {
        return ticketsRepository.save(tickets).getTicket_no();
    }
    @Transactional
    public boolean delete(String id) {
        var maybeAircraft = ticketsRepository.findById(id);
        maybeAircraft.ifPresent(aircraft -> ticketsRepository.delete(id));
        return maybeAircraft.isPresent();
    }
    public Optional<tickets> findById(String id) {
        return ticketsRepository.findById(id);
    }
    public List<tickets> findAll() {
        return ticketsRepository.findAll();
    }
    public void update(tickets tickets) {
        ticketsRepository.update(tickets);
    }
}
