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

@WebServlet("/flights/popularAircraft")
public class FlightsMostPopularAircraftServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            FlightsService flightsService = new FlightsService(new FlightsRepository(session));
            PrintWriter printWriter = resp.getWriter();
            var f = flightsService.getMostPopularAircraft();
            printWriter.write(
                    """
                        %s %s %s 
                       """.formatted(f.aircraft_code(), f.model(), f.count()));
            session.getTransaction().commit();
        }
    }
}
