package com.xx1ee.repos;

import com.xx1ee.classes.BoardingPassesPK;
import com.xx1ee.entity.BaseEntity;
import com.xx1ee.entity.boarding_passes;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class BoardingPassesRepository implements Repository<BoardingPassesPK, BaseEntity> {
    private final EntityManager entityManager;
    @Override
    public BaseEntity save(BaseEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(BoardingPassesPK id) {
        entityManager.detach(id);
        entityManager.flush();
    }

    @Override
    public void update(BaseEntity entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    @Override
    public Optional<BaseEntity> findById(BoardingPassesPK id) {
        return Optional.of(entityManager.find(boarding_passes.class, id));
    }

    @Override
    public List<BaseEntity> findAll() {
        return entityManager.createNativeQuery("select * from bookings.boarding_passes", boarding_passes.class).getResultList();
    }
}
