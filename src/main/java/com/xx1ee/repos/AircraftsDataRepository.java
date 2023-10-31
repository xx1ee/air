package com.xx1ee.repos;

import com.xx1ee.entity.BaseEntity;
import com.xx1ee.entity.aircrafts_data;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class AircraftsDataRepository implements Repository<String, BaseEntity> {
    private final EntityManager entityManager;
    @Override
    public BaseEntity save(BaseEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        entityManager.detach(id);
        entityManager.flush();
    }

    @Override
    public void update(BaseEntity entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public Optional<BaseEntity> findById(String id) {
        return Optional.of(entityManager.find(aircrafts_data.class, id));
    }

    @Override
    public List<BaseEntity> findAll() {
        return entityManager.createNativeQuery("select * from bookings.aircrafts_data", aircrafts_data.class).getResultList();
    }
}
