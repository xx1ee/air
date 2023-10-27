import com.xx1ee.classes.BoardingPassesPK;
import com.xx1ee.classes.SeatsPK;
import com.xx1ee.classes.Status;
import com.xx1ee.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class Test {
    @org.junit.jupiter.api.Test
    void test1() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            tickets e = session.createNativeQuery("SELECT * FROM tickets  " +
                    "e WHERE e.ticket_no = '0005432000861'", tickets.class).getSingleResult();
            System.out.println(e.getContact_data().getEmail());
            System.out.println(e.getContact_data().getPhone());
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test2() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            aircrafts_data e = session.createNativeQuery("SELECT * FROM aircrafts_data  " +
                    "e WHERE e.aircraft_code = '319'", aircrafts_data.class).getSingleResult();
            System.out.println(e.getRange());
            System.out.println(e.getModel().getEn());
            System.out.println(e.getModel().getRu());
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test3() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            airports_data e = session.createNativeQuery("SELECT * FROM airports_data  " +
                    "e WHERE e.airport_code = 'BZK'", airports_data.class).getSingleResult();
            System.out.println(e.getAirport_code());
            System.out.println(e.getAirport_name().getEn());
            System.out.println(e.getCoordinates());
            System.out.println(e.getTimezone());
            System.out.println(e.getDepartureList().get(0).getAircraft_code());
            System.out.println(e.getDepartureList().get(0).getActual_departure());
            System.out.println(e.getDepartureList().get(0).getStatus());
            System.out.println(e.getCity().getEn() + " " + e.getCity().getRu());
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test4() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var e = session.createNativeQuery("SELECT * FROM boarding_passes  " +
                    "e WHERE e.ticket_no = '0005432325079'", boarding_passes.class).getResultList();
            for (var s : e) {
                System.out.println("flight_id " + s.getBoardingPassesPK().getFlight_id());
                //System.out.println("seat_no " + s.getSeat_no());
                System.out.println("boarding " + s.getBoarding_no());
                System.out.println("amount " + s.getTicket_flights().getAmount());
            }
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test5() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var e = session.createNativeQuery("SELECT * FROM boarding_passes  " +
                    "e WHERE e.flight_id = 543", boarding_passes.class).getResultList();
            for (var s : e) {
                System.out.println("flight_id " + s.getBoardingPassesPK().getFlight_id());
                //System.out.println("seat_no " + s.getSeat_no());
                System.out.println("boarding " + s.getBoarding_no());
                System.out.println("amount " + s.getTicket_flights().getAmount() + "\n");
            }
            Assertions.assertEquals(381, e.size());
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test6() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var e = session.createNativeQuery("SELECT * FROM bookings  " +
                    "e WHERE e.book_ref = '3B54BB'", bookings.class).getResultList();

            for (var s : e) {
                System.out.println(s.getBook_ref() + " ");
                System.out.println(s.getBook_date() + " ");
                System.out.println(s.getTotal_amount() + "\n");
                var tickets = s.getTickets();
                for (var t : tickets) {
                    System.out.println(t.getPassenger_name());
                    System.out.println(t.getTicket_no());
                    System.out.println(t.getContact_data().getPhone() + " " + t.getContact_data().getEmail());
                }
            }
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test7() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            var e = session.createNativeQuery("SELECT * FROM flights  " +
                    "e WHERE e.flight_no = 'PG0159'", flights.class).getResultList();
            for (var s : e) {
                System.out.println(s.getFlight_id());
                System.out.println(s.getStatus() + " ");
                System.out.println(s.getAircraft_code().getModel().getRu() + " ");
                System.out.println(s.getActual_departure() + " ");
                System.out.println(s.getArrival_airport().getAirport_name().getRu());
                System.out.println(s.getDeparture_airport().getAirport_name().getRu());
                System.out.println(s.getScheduled_arrival() + "\n");
            }
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test8() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            aircrafts_data aircrafts_data = session.get(com.xx1ee.entity.aircrafts_data.class, 319);
            seats seats = com.xx1ee.entity.seats.builder().seatsPK(new SeatsPK("319", "6666")).aircraft_code(aircrafts_data).fare_conditions("Economy").build();
            session.persist(seats);
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test9() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            ticket_flights ticket_flights = session.get(ticket_flights.class, new BoardingPassesPK("0005432000860", 57376));
            System.out.println(ticket_flights.getTickets().getTicket_no());
            System.out.println(ticket_flights.getFlights().getFlight_id());
            System.out.println(ticket_flights.getAmount());
            tickets tickets = session.get(com.xx1ee.entity.tickets.class, "0005432000860");
            flights flights = session.get(com.xx1ee.entity.flights.class, 1);
            com.xx1ee.entity.ticket_flights ticket_flights1 = com.xx1ee.entity.ticket_flights.
                    builder().boardingPassesPK(new BoardingPassesPK(tickets.getTicket_no(), flights.getFlight_id())).tickets(tickets).flights(flights).amount((long) 7777.00).fare_conditions("Economy").build();
            session.persist(ticket_flights1);
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test10() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            tickets ticket = session.get(com.xx1ee.entity.tickets.class, "0005432000892");
            System.out.println(ticket.getTicket_no());
            System.out.println(ticket.getPassenger_id());
            System.out.println(ticket.getBook_ref().getBook_ref());
            System.out.println(ticket.getContact_data().getPhone() + " " + ticket.getContact_data().getEmail());
            session.getTransaction().commit();
        }
    }
}
