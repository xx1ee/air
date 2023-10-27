package com.xx1ee.dao;

import com.xx1ee.entity.BaseEntity;
import com.xx1ee.entity.aircrafts_data;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class AircraftsDataDao implements Dao<String, aircrafts_data>{
    private final SessionFactory sessionFactory;
    @Override
    public aircrafts_data save(aircrafts_data entity) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(aircrafts_data entity) {

    }

    @Override
    public Optional<aircrafts_data> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<aircrafts_data> findAll() {
        return null;
    }
}
