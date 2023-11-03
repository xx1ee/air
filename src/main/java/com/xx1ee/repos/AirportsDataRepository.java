package com.xx1ee.repos;

import com.xx1ee.entity.BaseEntity;
import com.xx1ee.entity.airports_data;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class AirportsDataRepository implements Repository<String, airports_data> {
    private final Session entityManager;
    @Transactional
    @Override
    public airports_data save(airports_data entity) {
        entityManager.persist(entity);
        return entity;
    }
    @Override
    public void delete(String id) {
        entityManager.detach(id);
    }

    @Override
    public void update(airports_data entity) {
        entityManager.merge(entity);
    }

    @Override
    public Optional<airports_data> findById(String id) {
        return Optional.of(entityManager.find(airports_data.class, id));
    }

    @Override
    public List<airports_data> findAll() {
        return entityManager.createNativeQuery("select * from bookings.airports_data", airports_data.class).getResultList();
    }
}
