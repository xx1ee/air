package com.xx1ee.repos;

import com.xx1ee.entity.bookings;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class BookingsRepository implements Repository<String, bookings> {
    private final Session session;
    @Override
    public bookings save(bookings entity) {
        session.persist(entity);
        return entity;
    }

    @Override
    public void delete(bookings id) {
        session.delete(id);
    }

    @Override
    public void update(bookings entity) {
        session.merge(entity);
    }

    @Override
    public Optional<bookings> findById(String id) {
        return Optional.of(session.find(bookings.class, id));
    }

    @Override
    public List<bookings> findAll() {
        return session.createNativeQuery("select * from bookings.bookings limit 100",bookings.class).getResultList();
    }
}
