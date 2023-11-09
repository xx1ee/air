package com.xx1ee.repos;

import com.xx1ee.entity.flights;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class FlightsRepository implements Repository<Integer, flights> {
    private final Session session;
    @Override
    public flights save(flights entity) {
        session.persist(entity);
        return entity;
    }

    @Override
    public void delete(flights id) {
        session.delete(id);
    }

    @Override
    public void update(flights entity) {
        session.merge(entity);
        session.flush();
    }

    @Override
    public Optional<flights> findById(Integer id) {
        return Optional.of(session.find(flights.class, id));
    }

    @Override
    public List<flights> findAll() {
        return session.createNativeQuery("select * from bookings.flights", flights.class).getResultList();
    }
}
