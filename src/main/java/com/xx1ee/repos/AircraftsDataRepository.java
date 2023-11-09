package com.xx1ee.repos;

import com.xx1ee.entity.aircrafts_data;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class AircraftsDataRepository implements Repository<String,aircrafts_data> {
    private final Session session;
    @Override
    public aircrafts_data save(aircrafts_data entity) {
        session.persist(entity);
        return entity;
    }

    @Override
    public void delete(aircrafts_data id) {
        session.delete(id);
        session.flush();
    }

    @Override
    public void update(aircrafts_data entity) {
        session.merge(entity);
        session.flush();
    }

    @Override
    public Optional<aircrafts_data> findById(String id) {
        return Optional.of(session.find(aircrafts_data.class, id));
    }

    @Override
    public List<aircrafts_data> findAll() {
        return session.createNativeQuery("select * from bookings.aircrafts_data", aircrafts_data.class).getResultList();
    }
}
