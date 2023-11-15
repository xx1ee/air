package com.xx1ee.repos;

import com.xx1ee.entity.BaseEntity;
import com.xx1ee.entity.tickets;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TicketsRepository implements Repository<String, tickets> {
    private final Session entityManager;

    @Override
    public tickets save(tickets entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(tickets id) {
        entityManager.delete(id);
    }

    @Override
    public void update(tickets entity) {
        entityManager.merge(entity);
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
