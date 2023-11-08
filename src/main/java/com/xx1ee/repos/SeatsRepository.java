package com.xx1ee.repos;

import com.xx1ee.classes.SeatsPK;
import com.xx1ee.entity.BaseEntity;
import com.xx1ee.entity.seats;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SeatsRepository implements Repository<SeatsPK, seats> {
    private final EntityManager entityManager;

    @Override
    public seats save(seats entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(SeatsPK id) {
        entityManager.detach(id);
        entityManager.flush();
    }

    @Override
    public void update(seats entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public Optional<seats> findById(SeatsPK id) {
        return Optional.of(entityManager.find(seats.class, id));
    }

    @Override
    public List<seats> findAll() {
        return entityManager.createNativeQuery("select * from bookings.seats", seats.class).getResultList();
    }
}
