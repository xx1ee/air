package com.xx1ee.repos;

import com.xx1ee.entity.BaseEntity;
import com.xx1ee.entity.tickets;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TicketsRepository implements Repository<String, tickets> {
    private final EntityManager entityManager;

    @Override
    public tickets save(tickets entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(String id) {
        entityManager.detach(id);
        entityManager.flush();
    }

    @Override
    public void update(tickets entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public Optional<tickets> findById(String id) {
        return Optional.of(entityManager.find(tickets.class, id));
    }

    @Override
    public List<tickets> findAll() {
        return entityManager.createNativeQuery("select * from bookings.tickets", tickets.class).getResultList();
    }
}
