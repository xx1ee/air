package com.xx1ee.repos;

import com.xx1ee.entity.BaseEntity;
import com.xx1ee.entity.bookings;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class BookingsRepository implements Repository<String, bookings> {
    private final EntityManager entityManager;
    @Override
    public bookings save(bookings entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        entityManager.detach(id);
        entityManager.flush();
    }

    @Override
    public void update(bookings entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public Optional<bookings> findById(String id) {
        return Optional.of(entityManager.find(bookings.class, id));
    }

    @Override
    public List<bookings> findAll() {
        return entityManager.createNativeQuery("select * from bookings.bookings",bookings.class).getResultList();
    }
}
