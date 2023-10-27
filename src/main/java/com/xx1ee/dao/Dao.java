package com.xx1ee.dao;

import com.xx1ee.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Dao<K extends Serializable, E extends BaseEntity> {
    E save(E entity);
    void delete(K id);
    void update(E entity);
    Optional<E> findById(K id);
    List<E> findAll();
}
