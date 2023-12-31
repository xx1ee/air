package com.xx1ee.repos;

import com.xx1ee.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<K extends Serializable, E extends BaseEntity> {
    E save(E entity);
    void delete(E id);
    void update(E entity);
    Optional<E> findById(K id);
    List<E> findAll();
}
