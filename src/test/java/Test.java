import com.xx1ee.classes.*;
import com.xx1ee.dto.AirportsDataCreateDto;
import com.xx1ee.dto.BoardingPassesCreateDto;
import com.xx1ee.entity.*;
import com.xx1ee.mapper.AirportsDataCreateMapper;
import com.xx1ee.mapper.AirportsDataReadMapper;
import com.xx1ee.mapper.BoardingPassesCreateMapper;
import com.xx1ee.mapper.BoardingPassesReadMapper;
import com.xx1ee.repos.*;
import com.xx1ee.service.*;
import jakarta.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Assertions;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Coordinates;
import org.locationtech.jts.geom.GeometryFactory;


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
            TicketsService ticketsService = new TicketsService(new TicketsRepository(session));
            FlightsService flightsService = new FlightsService(new FlightsRepository(session));
            ticket_flights ticket_flights = session.get(ticket_flights.class, new TicketFlightsId(ticketsService.findById("0005432000860").get(), flightsService.findById(57376).get()));
            System.out.println(ticket_flights.getTicketFlightsId().getFlights());
            System.out.println(ticket_flights.getTicketFlightsId().getTickets());
            System.out.println(ticket_flights.getAmount());
            tickets tickets = session.get(com.xx1ee.entity.tickets.class, "0005432000861");
            flights flights = session.get(com.xx1ee.entity.flights.class, 1);
            com.xx1ee.entity.ticket_flights ticket_flights1 = com.xx1ee.entity.ticket_flights.
                    builder().ticketFlightsId(new TicketFlightsId(tickets, flights)).amount((long) 7777.00).fare_conditions("Economy").build();
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
            //session.persist(t);
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
            TicketsService ticketsService = new TicketsService(new TicketsRepository(session));
            FlightsService flightsService = new FlightsService(new FlightsRepository(session));
            TicketFlightsService ticketFlightsService = new TicketFlightsService(new TicketFlightsRepository(session));
            ticketFlightsService.create(ticket_flights.builder()
                    .ticketFlightsId(new TicketFlightsId(ticketsService.findById("0123").get(), flightsService.findById(125565).get()))
                    .fare_conditions(String.valueOf(FareCondition.Business))
                    .amount(25000L)
                    .build());
            session.getTransaction().commit();
        }
    }

    @org.junit.jupiter.api.Test
    void test18() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            AircraftsDataService aircraftsDataService = new AircraftsDataService(new AircraftsDataRepository(session));
            var entity = aircrafts_data.builder()
                    .model(new Model("Shmurd182", "Шмурд182"))
                    .range(3000)
                    .aircraft_code("182")
                    .build();
            aircraftsDataService.create(entity);
            session.getTransaction().commit();
            session.beginTransaction();
            Assertions.assertTrue(aircraftsDataService.delete("182"));
            aircraftsDataService.create(entity);
            entity.setModel(new Model("Shmurdyak182", "Шмурдяк182"));
            aircraftsDataService.update(entity);
            var list = aircraftsDataService.findAll();
            session.getTransaction().commit();
            list.forEach(l -> System.out.println(l.getAircraft_code() + " " + l.getModel().getRu() + " " + l.getRange()));
        }
    }
    @org.junit.jupiter.api.Test
    void test19() {
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coordinate = new Coordinate(33.9, -118.4);
        var p = geometryFactory.createPoint(coordinate);
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            AirportsDataService airportsDataService = new AirportsDataService(new AirportsDataRepository(session), new AirportsDataReadMapper(), new AirportsDataCreateMapper());
            var airport = airports_data.builder()
                            .airport_code("LAX")
                                    .airport_name(new AirportName("Los Angeles International Airport",
                                            "Лос-Анджелес"))
                    .city(new City("LA", "Лос-Анджелес"))
                    .coordinates(p)
                    .timezone("Pacific Standart Time")
                    .build();
            System.out.println(airport.toString());
            var air = new AirportsDataCreateDto(airport.getAirport_code(), airport.getAirport_name(), airport.getCity(),
                    p, airport.getTimezone());
            airportsDataService.save(air);
            session.getTransaction().commit();
            session.beginTransaction();
            Assertions.assertTrue(airportsDataService.delete("LAX"));
            session.getTransaction().commit();
            session.beginTransaction();
            airportsDataService.save(air);
            airport.setTimezone("LA timezone");
            airport.setAirport_name(new AirportName("Los-Angeles International Airport", "Международный аэропорт Лос-Анджелес"));
            airportsDataService.update(airport);
            var list = airportsDataService.findAll();
            list.forEach(l -> System.out.println(l.getAirport_code() + " " + l.getAirport_name().getRu() + " " + l.getCity().getRu() + " " + l.getTimezone() + " " + l.getCoordinates()));
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test20() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            TicketsService ticketsService = new TicketsService(new TicketsRepository(session));

            session.getTransaction().commit();
        }
    }

    @org.junit.jupiter.api.Test
    void test21() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            BookingsService bookingsService = new BookingsService(new BookingsRepository(session));
            bookings bookings = com.xx1ee.entity.bookings.builder()
                    .book_ref("0000W")
                    .book_date(OffsetDateTime.of(2017,10,10,10,0,0,0, ZoneOffset.ofHours(3)))
                    .total_amount(29000L)
                    .build();
            bookingsService.delete("0000W");
            bookingsService.create(bookings);
            var findB = bookingsService.findById("0000W");
            findB.ifPresent(f -> System.out.println(findB.get().toString()));
            bookings.setTotal_amount(27000L);
            bookingsService.update(bookings);
            var l = bookingsService.findAll();
            System.out.println(l.size());
            //l.forEach(d -> System.out.println(d.getBook_ref() + " " + d.getBook_date() + " " + d.getTotal_amount()));
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test22() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            TicketsService ticketsService = new TicketsService(new TicketsRepository(session));
            //ticketsService.delete("0124");
            tickets t = tickets.builder()
                    .ticket_no("0124")
                    .passenger_id("0123")
                    .passenger_name("BORIS BAHLYKOV")
                    .book_ref((bookings) new BookingsRepository(session).findById("0000W").get())
                    .contact_data(new ContactData("bor@gmail.com", "+79891630718"))
                    .build();
            //ticketsService.create(t);
            t.setPassenger_name("BORIS BAHLIKOV");
            ticketsService.update(t);
            System.out.println(ticketsService.findById(t.getTicket_no()).get().getPassenger_name() + " ");
            BookingsService bookingsService = new BookingsService(new BookingsRepository(session));
            System.out.println(bookingsService.findById("0000W").get().getTickets().get(0).getPassenger_name());
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test23() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            FlightsService flightsService = new FlightsService(new FlightsRepository(session));
            AirportsDataService airportsDataService = new AirportsDataService(new AirportsDataRepository(session), new AirportsDataReadMapper(), new AirportsDataCreateMapper());
            AircraftsDataService aircraftsDataService = new AircraftsDataService(new AircraftsDataRepository(session));
            var dto = airportsDataService.findById("LAX").get();
            var kzn = airportsDataService.findById("PEZ").get();
            var plane = aircraftsDataService.findById("182").get();
            var flight = flights.builder()
                    .flight_no("PG6066")
                    .scheduled_departure(OffsetDateTime.of(2017, 1, 1, 8, 0, 0, 0, ZoneOffset.ofHours(4)))
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
                    .status(String.valueOf(Status.Arrived))
                    .aircraft_code(plane)

                    .build();
            //flightsService.delete(125566);
            //AirportsDataRepository airportsDataRepository = new AirportsDataRepository(sessionFactory.getCurrentSession());
            System.out.println(flightsService.create(flight));
            flight.setActual_departure(OffsetDateTime.of(2017, 1, 1, 8, 15, 0, 0, ZoneOffset.ofHours(4)));
            flight.setActual_arrival(OffsetDateTime.of(2017, 1, 1, 14, 15, 0, 0, ZoneOffset.ofHours(4)));
            flightsService.update(flight);
            //System.out.println(flightsService.findById());
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test24() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            TicketsService ticketsService = new TicketsService(new TicketsRepository(session));
            FlightsService flightsService = new FlightsService(new FlightsRepository(session));
            TicketFlightsService ticketFlightsService = new TicketFlightsService(new TicketFlightsRepository(session));
            //ticketFlightsService.delete(new TicketFlightsId("0124", 125567));
            ticket_flights ticket_flights = com.xx1ee.entity.ticket_flights.builder()
                    .ticketFlightsId(new TicketFlightsId(ticketsService.findById("0124").get(), flightsService.findById(125567).get()))
                    .fare_conditions(String.valueOf(FareCondition.Economy))
                    .amount(9000L)
                    .build();
            ticket_flights ticket_flights1 = com.xx1ee.entity.ticket_flights.builder()
                    .ticketFlightsId(new TicketFlightsId(ticketsService.findById("00054322222").get(), flightsService.findById(125567).get()))
                    .fare_conditions(String.valueOf(FareCondition.Economy))
                    .amount(9500L)
                    .build();
            //ticketFlightsService.create(ticket_flights1);
            //var tf =ticketFlightsService.findById(new TicketFlightsId(ticketsService.findById("0124").get(), flightsService.findById(125567).get())).get();
            System.out.println(flightsService.findById(125567).get().getTicketsList().size());
            System.out.println(flightsService.findById(125567).get().getTicketsList().get(0).getTicketFlightsId().getTickets().getTicket_no());
            System.out.println(flightsService.findById(125567).get().getTicketsList().get(1).getTicketFlightsId().getTickets().getTicket_no());
            //System.out.println(ticketFlightsService.findById(new TicketFlightsId("0124", 125567)).get().getFlights().getDeparture_airport());
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    @Transactional
    void test25() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            BoardingPassesService boardingPassesService = new BoardingPassesService(new BoardingPassesRepository(session), new BoardingPassesCreateMapper(), new BoardingPassesReadMapper());
            //System.out.println(ticketFlightsService.findById(new TicketFlightsId("0124", 125567)).get().getFlights().getDeparture_airport());
            boardingPassesService.save(new BoardingPassesCreateDto(new BoardingPassesPK("00054322222", 125567), 666, "22A"));
            var bp = boardingPassesService.findById(new BoardingPassesPK("00054322222", 125567)).get();
            //boardingPassesService.delete(bp.boardingPassesPK());
            session.getTransaction().commit();
        }
    }
}

