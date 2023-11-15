package com.xx1ee.repos;

import com.xx1ee.classes.SeatsPK;
import com.xx1ee.entity.BaseEntity;
import com.xx1ee.entity.seats;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SeatsRepository implements Repository<SeatsPK, seats> {
    private final Session entityManager;

    @Override
    public seats save(seats entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(seats id) {
        entityManager.delete(id);
    }

    @Override
    public void update(seats entity) {
        entityManager.merge(entity);
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
