package com.xx1ee.service;

import com.xx1ee.classes.TicketFlightsId;
import com.xx1ee.entity.ticket_flights;
import com.xx1ee.entity.tickets;
import com.xx1ee.repos.TicketFlightsRepository;
import com.xx1ee.repos.TicketsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class TicketFlightsService {
    private final TicketFlightsRepository ticketFlightsRepository;
    public TicketFlightsId create(ticket_flights ticket_flights) {
        return ticketFlightsRepository.save(ticket_flights).getTicketFlightsId();
    }
    @Transactional
    public boolean delete(TicketFlightsId id) {
        var maybeAircraft = ticketFlightsRepository.findById(id);
        maybeAircraft.ifPresent(aircraft -> ticketFlightsRepository.delete(id));
        return maybeAircraft.isPresent();
    }
    public Optional<ticket_flights> findById(TicketFlightsId id) {
        return ticketFlightsRepository.findById(id);
    }
    public List<ticket_flights> findAll() {
        return ticketFlightsRepository.findAll();
    }
    public void update(ticket_flights tickets_flights) {
        ticketFlightsRepository.update(tickets_flights);
    }
}
