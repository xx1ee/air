import com.xx1ee.classes.*;
import com.xx1ee.dto.BoardingPassesCreateDto;
import com.xx1ee.entity.*;
import com.xx1ee.mapper.AirportsDataCreateMapper;
import com.xx1ee.mapper.AirportsDataReadMapper;
import com.xx1ee.mapper.BoardingPassesCreateMapper;
import com.xx1ee.mapper.BoardingPassesReadMapper;
import com.xx1ee.repos.*;
import com.xx1ee.service.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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
            seats seats = com.xx1ee.entity.seats.builder().seatsPK(new SeatsPK("319", "6667")).aircraft_code(aircrafts_data).fare_conditions(String.valueOf(FareCondition.Economy)).build();
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
            ticket_flights ticket_flights = session.get(ticket_flights.class, new TicketFlightsId("0005432000860", 57376));
            System.out.println(ticket_flights.getTickets().getTicket_no());
            System.out.println(ticket_flights.getFlights().getFlight_id());
            System.out.println(ticket_flights.getAmount());
            tickets tickets = session.get(com.xx1ee.entity.tickets.class, "0005432000861");
            flights flights = session.get(com.xx1ee.entity.flights.class, 1);
            com.xx1ee.entity.ticket_flights ticket_flights1 = com.xx1ee.entity.ticket_flights.
                    builder().ticketFlightsId(new TicketFlightsId(tickets.getTicket_no(), flights.getFlight_id())).tickets(tickets).flights(flights).amount((long) 7777.00).fare_conditions("Economy").build();
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

    @org.junit.jupiter.api.Test
    void test11() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            BoardingPassesService boardingPassesService = new BoardingPassesService(new BoardingPassesRepository(session),
                    new BoardingPassesCreateMapper(), new BoardingPassesReadMapper());
            var bp = boardingPassesService.findById(new BoardingPassesPK("0005432000860", 57376));
            System.out.println(bp.get().boarding_no());
            System.out.println(bp.get().seat_no());
            var bp1 = boarding_passes.builder().boardingPassesPK(new BoardingPassesPK("0005432000862", 1))
                    .boarding_no(104)
                    .seat_no("22L")
                    .build();
            boardingPassesService.save(new BoardingPassesCreateDto(new BoardingPassesPK("0005432000862", 7), 104, "22G"));
            session.getTransaction().commit();
        }
    }

    @org.junit.jupiter.api.Test
    void test12() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            //AirportsDataRepository airportsDataRepository = new AirportsDataRepository(sessionFactory.getCurrentSession());
            airports_data a = session.get(airports_data.class, "BTK");
            System.out.println(a.getCoordinates());
            airports_data ab = airports_data.builder().airport_code("BEE")
                    .coordinates(a.getCoordinates())
                    .airport_name(a.getAirport_name())
                    .city(a.getCity())
                    .timezone(a.getTimezone())
                    .build();
            session.persist(ab);
            session.getTransaction().commit();
        }
    }

    @org.junit.jupiter.api.Test
    void test13() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            //AirportsDataRepository airportsDataRepository = new AirportsDataRepository(sessionFactory.getCurrentSession());
            tickets a = session.get(tickets.class, "0005432000860");
            System.out.println(a.getContact_data().getPhone());
            tickets t = tickets.builder()
                    .ticket_no("0123")
                    .passenger_id("0123")
                    .passenger_name("BORIS BAHLYKOV")
                    .book_ref((bookings) new BookingsRepository(session).findById("486678").get())
                    .contact_data(new ContactData("bor@gmail.com", "+79891630718"))
                    .build();
            session.persist(t);
            session.getTransaction().commit();
        }
    }

    @org.junit.jupiter.api.Test
    void test14() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            //AirportsDataRepository airportsDataRepository = new AirportsDataRepository(sessionFactory.getCurrentSession());
            bookings t = bookings.builder().book_ref("00000W")
                    .book_date(OffsetDateTime.of(2017, 11, 13, 14, 30, 0, 0, ZoneOffset.ofHours(3)))
                    .total_amount(13000L)
                    .build();
            session.persist(t);
            session.getTransaction().commit();
        }
    }

    @org.junit.jupiter.api.Test
    void test15() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            SeatsService service = new SeatsService(new SeatsRepository(session));
            System.out.println(FareCondition.Economy);
            //AirportsDataRepository airportsDataRepository = new AirportsDataRepository(sessionFactory.getCurrentSession());
            service.create(seats.builder()
                    .seatsPK(new SeatsPK("319", "4G"))
                    .fare_conditions(String.valueOf(FareCondition.Economy))
                    .build());
            session.getTransaction().commit();
        }
    }

    @org.junit.jupiter.api.Test
    void test16() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            FlightsService flightsService = new FlightsService(new FlightsRepository(session));
            AirportsDataService airportsDataService = new AirportsDataService(new AirportsDataRepository(session), new AirportsDataReadMapper(), new AirportsDataCreateMapper());
            AircraftsDataService aircraftsDataService = new AircraftsDataService(new AircraftsDataRepository(session));
            var dto = airportsDataService.findById("BEB").get();
            var kzn = airportsDataService.findById("KZN").get();
            var plane = aircraftsDataService.findById("CR2").get();
            //AirportsDataRepository airportsDataRepository = new AirportsDataRepository(sessionFactory.getCurrentSession());
            flightsService.create(flights.builder()
                    .flight_no("PG6666")
                    .scheduled_departure(OffsetDateTime.of(2017, 1, 1, 12, 0, 0, 0, ZoneOffset.ofHours(4)))
                    .scheduled_arrival(OffsetDateTime.of(2017, 1, 1, 14, 30, 0, 0, ZoneOffset.ofHours(4)))
                    .departure_airport(airports_data.builder().airport_code(dto.airports_code())
                            .coordinates(dto.coordinates())
                            .city(dto.city())
                            .timezone(dto.timezone())
                            .build())
                    .arrival_airport(airports_data.builder().airport_code(kzn.airports_code())
                            .coordinates(kzn.coordinates())
                            .city(kzn.city())
                            .timezone(kzn.timezone())
                            .build())
                    .status(String.valueOf(Status.Cancelled))
                    .aircraft_code(plane)

                    .build());
            session.getTransaction().commit();
        }
    }

    @org.junit.jupiter.api.Test
    void test17() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            TicketFlightsService ticketFlightsService = new TicketFlightsService(new TicketFlightsRepository(session));
            ticketFlightsService.create(ticket_flights.builder()
                    .ticketFlightsId(new TicketFlightsId("0123", 125565))
                    .fare_conditions(String.valueOf(FareCondition.Business))
                    .amount(25000L)
                    .build());
            session.getTransaction().commit();
        }
    }


}

