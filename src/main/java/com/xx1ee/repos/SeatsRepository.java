package com.xx1ee.repos;

import com.xx1ee.classes.SeatsPK;
import com.xx1ee.entity.BaseEntity;
import com.xx1ee.entity.seats;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SeatsRepository implements Repository<SeatsPK, BaseEntity> {
    private final EntityManager entityManager;

    @Override
    public BaseEntity save(BaseEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(SeatsPK id) {
        entityManager.detach(id);
        entityManager.flush();
    }

    @Override
    public void update(BaseEntity entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public Optional<BaseEntity> findById(SeatsPK id) {
        return Optional.of(entityManager.find(seats.class, id));
    }

    @Override
    public List<BaseEntity> findAll() {
        return entityManager.createNativeQuery("select * from bookings.seats", seats.class).getResultList();
    }
}
