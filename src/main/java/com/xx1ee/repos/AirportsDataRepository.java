package com.xx1ee.repos;

import com.xx1ee.entity.airports_data;
import com.xx1ee.entity.flights;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class AirportsDataRepository implements Repository<String, airports_data> {
    private final Session session;
    @Transactional
    @Override
    public airports_data save(airports_data entity) {
        session.persist(entity);
        return entity;
    }
    @Override
    public void delete(airports_data id) {
        session.delete(id);
    }

    @Override
    public void update(airports_data entity) {
        session.merge(entity);
    }

    @Override
    public Optional<airports_data> findById(String id) {
        return Optional.of(session.find(airports_data.class, id));
    }

    @Override
    public List<airports_data> findAll() {
        return session.createNativeQuery("select * from bookings.airports_data", airports_data.class).getResultList();
    }
}
