package com.xx1ee.repos;

import com.xx1ee.entity.BaseEntity;
import com.xx1ee.entity.flights;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class FlightsRepository implements Repository<Integer, flights> {
    private final EntityManager entityManager;
    @Override
    public flights save(flights entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(Integer id) {
        entityManager.detach(id);
        entityManager.flush();
    }

    @Override
    public void update(flights entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public Optional<flights> findById(Integer id) {
        return Optional.of(entityManager.find(flights.class, id));
    }

    @Override
    public List<flights> findAll() {
        return entityManager.createNativeQuery("select * from bookings.flights", flights.class).getResultList();
    }
}
