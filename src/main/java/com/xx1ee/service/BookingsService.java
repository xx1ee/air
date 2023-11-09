package com.xx1ee.service;

import com.xx1ee.entity.bookings;
import com.xx1ee.entity.tickets;
import com.xx1ee.repos.BookingsRepository;
import com.xx1ee.repos.TicketsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BookingsService {
    private final BookingsRepository bookingsRepository;
    public String create(bookings bookings) {
        return bookingsRepository.save(bookings).getBook_ref();
    }
    @Transactional
    public boolean delete(String id) {
        var maybeAircraft = bookingsRepository.findById(id);
        maybeAircraft.ifPresent(aircraft -> bookingsRepository.delete(maybeAircraft.get()));
        return maybeAircraft.isPresent();
    }
    public Optional<bookings> findById(String id) {
        return bookingsRepository.findById(id);
    }
    public List<bookings> findAll() {
        return bookingsRepository.findAll();
    }
    public void update(bookings bookings) {
        bookingsRepository.update(bookings);
    }
}
