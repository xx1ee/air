package com.xx1ee.servlet;

import com.xx1ee.repos.FlightsRepository;
import com.xx1ee.service.FlightsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/flights/mostPopularAircraft")
public class FlightsMostPopularAircraft extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            FlightsService flightsService = new FlightsService(new FlightsRepository(session));
            PrintWriter printWriter = resp.getWriter();
            var f = flightsService.getShortestFlight();
            printWriter.write(
                    """
                        %s %s %s %s %s %s
                       """.formatted(f.getFlight_no(), f.getAircraft_code(), f.getDeparture_airport(), f.getArrival_airport(), f.getScheduled_arrival(), f.getScheduled_departure())
            );
            session.getTransaction().commit();
        }
    }
}
