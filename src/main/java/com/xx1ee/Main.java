package com.xx1ee;

import com.xx1ee.classes.SeatsPK;
import com.xx1ee.entity.aircrafts_data;
import com.xx1ee.entity.tickets;
import com.xx1ee.repos.AircraftsDataRepository;
import com.xx1ee.repos.AirportsDataRepository;
import com.xx1ee.repos.SeatsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            var ar = new AirportsDataRepository(session);
            var se = ar.findById("AAQ").get();
            session.getTransaction().commit();
            //var e = se.getArrivalList().size();
        }
    }
}