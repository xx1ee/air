package com.xx1ee.repos;

import com.xx1ee.classes.BoardingPassesPK;
import com.xx1ee.classes.TicketFlightsId;
import com.xx1ee.entity.BaseEntity;
import com.xx1ee.entity.ticket_flights;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class TicketFlightsRepository implements Repository<TicketFlightsId, ticket_flights> {
    private final Session entityManager;
    @Override
    public ticket_flights save(ticket_flights entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(ticket_flights id) {
        entityManager.delete(id);
    }

    @Override
    public void update(ticket_flights entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public Optional<ticket_flights> findById(TicketFlightsId id) {
        return Optional.of(entityManager.find(ticket_flights.class, id));
    }

    @Override
    public List<ticket_flights> findAll() {
        return entityManager.createNativeQuery("select * from bookings.ticket_flights", ticket_flights.class).getResultList();
    }
}
