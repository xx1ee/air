package com.xx1ee.servlet;

import com.google.gson.Gson;
import com.xx1ee.classes.OffsetDateTimeCreate;
import com.xx1ee.classes.Status;
import com.xx1ee.dto.AirportsDataReadDto;
import com.xx1ee.entity.airports_data;
import com.xx1ee.entity.bookings;
import com.xx1ee.entity.flights;
import com.xx1ee.entity.tickets;
import com.xx1ee.mapper.AirportsDataCreateMapper;
import com.xx1ee.mapper.AirportsDataReadMapper;
import com.xx1ee.repos.AircraftsDataRepository;
import com.xx1ee.repos.AirportsDataRepository;
import com.xx1ee.repos.BookingsRepository;
import com.xx1ee.repos.FlightsRepository;
import com.xx1ee.service.AircraftsDataService;
import com.xx1ee.service.AirportsDataService;
import com.xx1ee.service.BookingsService;
import com.xx1ee.service.FlightsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

@WebServlet("/flights")
public class FlightsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            FlightsService flightsService = new FlightsService(new FlightsRepository(session));
            PrintWriter printWriter = resp.getWriter();
            flightsService.findAll().forEach(flights -> printWriter.write(
                    """
                        %s %s %s %s %s %s %s %s %s %s
                       """.formatted(flights.getFlight_id(), flights.getFlight_no(), flights.getScheduled_departure(), flights.getScheduled_arrival(),
                            flights.getDeparture_airport(), flights.getArrival_airport(), flights.getStatus(), flights.getAircraft_code(),
                            flights.getActual_arrival(), flights.getActual_departure())
            ));
            session.getTransaction().commit();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            FlightsService flightsService = new FlightsService(new FlightsRepository(session));
            flightsService.create(createFlight(req, session));
            session.getTransaction().commit();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            FlightsService flightsService = new FlightsService(new FlightsRepository(session));
            flightsService.delete(Integer.valueOf(req.getParameter("flight_id")));
            session.getTransaction().commit();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            FlightsService flightsService = new FlightsService(new FlightsRepository(session));
            flightsService.update(createFlight(req, session));
            session.getTransaction().commit();
        }
    }
    private static Map<String, String> getStringStringMap(HttpServletRequest req) throws IOException {
        List<String> arrayList = List.of("scheduled_departure","scheduled_arrival", "actual_departure", "actual_arrival", "end");
        Map<String, String> stringStringMap = new HashMap<>();
        BufferedReader reader = req.getReader();
        var start = reader.readLine();
        var scheduled_dep = reader.readLine();
        for (int i = 1; i < arrayList.size(); i++) {
            StringBuilder sb = new StringBuilder();
            String line;
            while (!Objects.equals(line = reader.readLine(), arrayList.get(i))) {
                sb.append(line);
            }
            stringStringMap.put(arrayList.get(i - 1), sb.toString());
        }
        return stringStringMap;
    }
    private static flights createFlight(HttpServletRequest req, Session session) throws IOException {
        Map<String, String> stringStringMap = getStringStringMap(req);
        Gson gson = new Gson();
        OffsetDateTimeCreate scheduled_departure = gson.fromJson(stringStringMap.get("scheduled_departure"), OffsetDateTimeCreate.class);
        OffsetDateTimeCreate scheduled_arrival = gson.fromJson(stringStringMap.get("scheduled_arrival"), OffsetDateTimeCreate.class);
        OffsetDateTimeCreate actual_departure = gson.fromJson(stringStringMap.get("actual_departure"), OffsetDateTimeCreate.class);
        OffsetDateTimeCreate actual_arrival = gson.fromJson(stringStringMap.get("actual_arrival"), OffsetDateTimeCreate.class);
        AircraftsDataService aircraftsDataService = new AircraftsDataService(new AircraftsDataRepository(session));
        AirportsDataService airportsDataService = new AirportsDataService(new AirportsDataRepository(session), new AirportsDataReadMapper(), new AirportsDataCreateMapper());
        var dto = airportsDataService.findById(req.getParameter("departure_airport")).get();
        var dto1 = airportsDataService.findById(req.getParameter("arrival_airport")).get();
        return flights.builder()
                .flight_no(req.getParameter("flight_no"))
                .departure_airport(airports_data.builder().airport_code(dto.airports_code())
                        .coordinates(dto.coordinates())
                        .city(dto.city())
                        .timezone(dto.timezone())
                        .build())
                .arrival_airport(airports_data.builder().airport_code(dto1.airports_code())
                        .coordinates(dto1.coordinates())
                        .city(dto1.city())
                        .timezone(dto1.timezone())
                        .build())
                .status(String.valueOf(Status.valueOf(req.getParameter("status"))))
                .aircraft_code(aircraftsDataService.findById(req.getParameter("aircraft_code")).get())
                .scheduled_departure(OffsetDateTime.of(scheduled_departure.getYear(), scheduled_departure.getMonth()
                        , scheduled_departure.getDayOfMonth(), scheduled_departure.getHour(), scheduled_departure.getMinute(), scheduled_departure.getSecond(),
                        scheduled_departure.getNanoOfSecond(), ZoneOffset.ofHours(scheduled_departure.getOffset())))
                .scheduled_arrival(OffsetDateTime.of(scheduled_arrival.getYear(), scheduled_arrival.getMonth()
                        , scheduled_arrival.getDayOfMonth(), scheduled_arrival.getHour(), scheduled_arrival.getMinute(), scheduled_arrival.getSecond(),
                        scheduled_arrival.getNanoOfSecond(), ZoneOffset.ofHours(scheduled_arrival.getOffset())))
                .actual_departure(OffsetDateTime.of(actual_departure.getYear(), actual_departure.getMonth()
                        , actual_departure.getDayOfMonth(), actual_departure.getHour(), actual_departure.getMinute(), actual_departure.getSecond(),
                        actual_departure.getNanoOfSecond(), ZoneOffset.ofHours(actual_departure.getOffset())))
                .actual_arrival(OffsetDateTime.of(actual_arrival.getYear(), actual_arrival.getMonth()
                        , actual_arrival.getDayOfMonth(), actual_arrival.getHour(), actual_arrival.getMinute(), actual_arrival.getSecond(),
                        actual_arrival.getNanoOfSecond(), ZoneOffset.ofHours(actual_arrival.getOffset())))
                .build();
    }
}
