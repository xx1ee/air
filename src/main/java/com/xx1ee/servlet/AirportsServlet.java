package com.xx1ee.servlet;

import com.google.gson.Gson;
import com.xx1ee.classes.AirportName;
import com.xx1ee.classes.City;
import com.xx1ee.classes.Model;
import com.xx1ee.dto.AirportsDataCreateDto;
import com.xx1ee.entity.airports_data;
import com.xx1ee.entity.flights;
import com.xx1ee.mapper.AirportsDataCreateMapper;
import com.xx1ee.mapper.AirportsDataReadMapper;
import com.xx1ee.repos.AircraftsDataRepository;
import com.xx1ee.repos.AirportsDataRepository;
import com.xx1ee.service.AircraftsDataService;
import com.xx1ee.service.AirportsDataService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.stream.Collectors;

@WebServlet("/airports")
public class AirportsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            AirportsDataService airportsDataService = new AirportsDataService(new AirportsDataRepository(session), new AirportsDataReadMapper(), new AirportsDataCreateMapper());
            PrintWriter printWriter = resp.getWriter();
            airportsDataService.findAll().forEach( airports_data -> printWriter.write(
                    """
                        (Airport Code)%s (Airport Name)%s %s (Coordinates)%s (Timezone)%s (Arrivals)%s (Departures)%s      
                       """.formatted(airports_data.getAirport_code(), airports_data.getAirport_name().getEn() + " " + airports_data.getAirport_name().getRu(),
                            airports_data.getCity().getEn()+ airports_data.getCity().getRu(), airports_data.getCoordinates().getCoordinate(), airports_data.getTimezone(),
                            airports_data.getArrivalList().stream().map(flights::getFlight_no).toList(),
                            airports_data.getDepartureList().stream().map(flights::getFlight_no).toList())
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
            AirportsDataService airportsDataService = new AirportsDataService(new AirportsDataRepository(session), new AirportsDataReadMapper(), new AirportsDataCreateMapper());
            StringBuilder name = new StringBuilder();
            StringBuilder city = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while (!Objects.equals(line = reader.readLine(), "City")) {
                name.append(line);
            }
            while (!Objects.equals(line = reader.readLine(), null)) {
                city.append(line);
            }
            String data1 = name.toString();
            String data2 = city.toString();
            Gson gson = new Gson();
            AirportName airportName = gson.fromJson(data1, AirportName.class);
            City airportCity = gson.fromJson(data2, City.class);
            var massCoord = req.getParameter("coordinates").trim().split(" ");
            GeometryFactory geometryFactory = new GeometryFactory();
            Coordinate coordinate = new Coordinate(Double.parseDouble(massCoord[0]), Double.parseDouble(massCoord[1]));
            var p = geometryFactory.createPoint(coordinate);
            var create = new AirportsDataCreateDto(req.getParameter("airport_code"), airportName,
                    airportCity, p, req.getParameter("timezone"));
            airportsDataService.save(create);
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
            AirportsDataService airportsDataService = new AirportsDataService(new AirportsDataRepository(session), new AirportsDataReadMapper(), new AirportsDataCreateMapper());
            airportsDataService.delete(req.getParameter("airport_code"));
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
            AirportsDataService airportsDataService = new AirportsDataService(new AirportsDataRepository(session), new AirportsDataReadMapper(), new AirportsDataCreateMapper());
            StringBuilder name = new StringBuilder();
            StringBuilder city = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while (!Objects.equals(line = reader.readLine(), "City")) {
                name.append(line);
            }
            while (!Objects.equals(line = reader.readLine(), null)) {
                city.append(line);
            }
            String data1 = name.toString();
            String data2 = city.toString();
            Gson gson = new Gson();
            AirportName airportName = gson.fromJson(data1, AirportName.class);
            City airportCity = gson.fromJson(data2, City.class);
            var massCoord = req.getParameter("coordinates").trim().split("\n");
            GeometryFactory geometryFactory = new GeometryFactory();
            Coordinate coordinate = new Coordinate(Double.parseDouble(massCoord[0]), Double.parseDouble(massCoord[1]));
            var p = geometryFactory.createPoint(coordinate);
            airportsDataService.update(airports_data.builder()
                            .airport_code(req.getParameter("airport_code"))
                            .airport_name(airportName)
                            .coordinates(p)
                            .city(airportCity)
                            .timezone(req.getParameter("timezone"))
                    .build());
            session.getTransaction().commit();
        }
    }
}
