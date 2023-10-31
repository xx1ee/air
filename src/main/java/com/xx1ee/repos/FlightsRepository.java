package com.xx1ee.repos;

import com.xx1ee.entity.BaseEntity;
import com.xx1ee.entity.flights;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class FlightsRepository implements Repository<Integer, BaseEntity> {
    private final EntityManager entityManager;
    @Override
    public BaseEntity save(BaseEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(Integer id) {
        entityManager.detach(id);
        entityManager.flush();
    }

    @Override
    public void update(BaseEntity entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public Optional<BaseEntity> findById(Integer id) {
        return Optional.of(entityManager.find(flights.class, id));
    }

    @Override
    public List<BaseEntity> findAll() {
        return entityManager.createNativeQuery("select * from bookings.flights", flights.class).getResultList();
    }
}
