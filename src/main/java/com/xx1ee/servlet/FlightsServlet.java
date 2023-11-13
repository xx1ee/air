package com.xx1ee.servlet;

import com.google.gson.Gson;
import com.xx1ee.classes.OffsetDateTimeCreate;
import com.xx1ee.entity.bookings;
import com.xx1ee.entity.flights;
import com.xx1ee.entity.tickets;
import com.xx1ee.repos.BookingsRepository;
import com.xx1ee.repos.FlightsRepository;
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
            Map<String, String> stringStringMap = getStringStringMap(req);
            Gson gson = new Gson();
            OffsetDateTimeCreate scheduled_departure = gson.fromJson(stringStringMap.get("scheduled_departure"), OffsetDateTimeCreate.class);
            OffsetDateTimeCreate scheduled_arrival = gson.fromJson(stringStringMap.get("scheduled_arrival"), OffsetDateTimeCreate.class);
            OffsetDateTimeCreate actual_departure = gson.fromJson(stringStringMap.get("actual_departure"), OffsetDateTimeCreate.class);
            OffsetDateTimeCreate actual_arrival = gson.fromJson(stringStringMap.get("actual_arrival"), OffsetDateTimeCreate.class);
            FlightsService flightsService = new FlightsService(new FlightsRepository(session));
            flightsService.create(flights.builder()
                            .flight_no(req.getParameter("flight_no"))
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
                    .build());
            session.getTransaction().commit();
        }
    }

    private static Map<String, String> getStringStringMap(HttpServletRequest req) throws IOException {
        List<String> arrayList = List.of("scheduled_departure","scheduled_arrival", "actual_departure", "actual_arrival");
        Map<String, String> stringStringMap = new HashMap<>();
        for (String s : arrayList) {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while (!Objects.equals(line = reader.readLine(), s)) {
                sb.append(line);
            }
            stringStringMap.put(s, sb.toString());
        }
        return stringStringMap;
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
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Map<String, String> stringStringMap = getStringStringMap(req);
            Gson gson = new Gson();
            OffsetDateTimeCreate scheduled_departure = gson.fromJson(stringStringMap.get("scheduled_departure"), OffsetDateTimeCreate.class);
            OffsetDateTimeCreate scheduled_arrival = gson.fromJson(stringStringMap.get("scheduled_arrival"), OffsetDateTimeCreate.class);
            OffsetDateTimeCreate actual_departure = gson.fromJson(stringStringMap.get("actual_departure"), OffsetDateTimeCreate.class);
            OffsetDateTimeCreate actual_arrival = gson.fromJson(stringStringMap.get("actual_arrival"), OffsetDateTimeCreate.class);
            FlightsService flightsService = new FlightsService(new FlightsRepository(session));
            flightsService.update(flights.builder()
                    .flight_id(Integer.valueOf(req.getParameter("flight_id")))
                    .flight_no(req.getParameter("flight_no"))
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
                    .build());
            session.getTransaction().commit();
        }
    }
}
