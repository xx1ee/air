package com.xx1ee;

import com.xx1ee.classes.AirportName;
import com.xx1ee.classes.City;
import com.xx1ee.entity.airports_data;
import com.xx1ee.mapper.AirportsDataReadMapper;
import com.xx1ee.repos.AirportsDataRepository;
import com.xx1ee.service.AirportsDataService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            session.getTransaction().commit();
        }
    }
}