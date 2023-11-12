package com.xx1ee.servlet;

import com.google.gson.Gson;
import com.xx1ee.classes.Model;
import com.xx1ee.entity.aircrafts_data;
import com.xx1ee.repos.AircraftsDataRepository;
import com.xx1ee.service.AircraftsDataService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/aircrafts")
public class AircraftsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            AircraftsDataService aircraftsDataService = new AircraftsDataService(new AircraftsDataRepository(session));
            PrintWriter printWriter = resp.getWriter();
            aircraftsDataService.findAll().forEach( aircrafts_data -> printWriter.write(
                    """
                        %s %d %s              
                       """.formatted(aircrafts_data.getAircraft_code(), aircrafts_data.getRange(), aircrafts_data.getModel().getEn())
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
            AircraftsDataService aircraftsDataService = new AircraftsDataService(new AircraftsDataRepository(session));
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            Model model = gson.fromJson(data, Model.class);
            var ent = aircrafts_data.builder()
                    .aircraft_code(req.getParameter("aircraft_code"))
                    .range(Integer.valueOf(req.getParameter("range")))
                    .model(model)
                    .build();
            aircraftsDataService.create(ent);
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
            AircraftsDataService aircraftsDataService = new AircraftsDataService(new AircraftsDataRepository(session));
            aircraftsDataService.delete(req.getParameter("id"));
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
            AircraftsDataService aircraftsDataService = new AircraftsDataService(new AircraftsDataRepository(session));
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            Model model = gson.fromJson(data, Model.class);
            var ent = aircrafts_data.builder()
                    .aircraft_code(req.getParameter("aircraft_code"))
                    .range(Integer.valueOf(req.getParameter("range")))
                    .model(model)
                    .build();
            aircraftsDataService.update(ent);
            session.getTransaction().commit();
        }
    }
}
