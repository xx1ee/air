package com.xx1ee;

import com.xx1ee.entity.aircrafts_data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            aircrafts_data e = session.createNativeQuery("SELECT * FROM aircrafts_data  " +
                    "e WHERE e.aircraft_code = '319'", aircrafts_data.class).getSingleResult();
            System.out.println(e.toString());
            System.out.println(e.getModel().getEn());
            session.getTransaction().commit();
        }
    }
}