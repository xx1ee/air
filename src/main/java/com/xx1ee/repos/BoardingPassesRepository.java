package com.xx1ee.repos;

import com.xx1ee.classes.BoardingPassesPK;
import com.xx1ee.entity.boarding_passes;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class BoardingPassesRepository implements Repository<BoardingPassesPK, boarding_passes> {
    private final Session session;
    @Override
    public boarding_passes save(boarding_passes entity) {
        session.persist(entity);
        return entity;
    }

    @Override
    public void delete(boarding_passes id) {
        session.delete(id);
    }

    @Override
    public void update(boarding_passes entity) {
        session.merge(entity);
    }

    @Override
    public Optional<boarding_passes> findById(BoardingPassesPK id) {
        return Optional.of(session.find(boarding_passes.class, id));
    }

    @Override
    public List<boarding_passes> findAll() {
        return session.createNativeQuery("select * from bookings.boarding_passes", boarding_passes.class).getResultList();
    }
}
