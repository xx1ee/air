package com.xx1ee.servlet;

import com.google.gson.Gson;
import com.xx1ee.classes.BoardingPassesPK;
import com.xx1ee.classes.SeatsPK;
import com.xx1ee.dto.BoardingPassesCreateDto;
import com.xx1ee.entity.seats;
import com.xx1ee.mapper.BoardingPassesCreateMapper;
import com.xx1ee.mapper.BoardingPassesReadMapper;
import com.xx1ee.repos.BoardingPassesRepository;
import com.xx1ee.repos.SeatsRepository;
import com.xx1ee.service.BoardingPassesService;
import com.xx1ee.service.SeatsService;
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

@WebServlet("/seats")
public class SeatsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            SeatsService service = new SeatsService(new SeatsRepository(session));
            PrintWriter printWriter = resp.getWriter();
            service.findAll().forEach(s -> printWriter.write(
                    """
                        %s %s %s    
                       """.formatted(s.getSeatsPK().getSeat_no(), s.getSeatsPK().getAircraft_code(), s.getFare_conditions())
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
            SeatsService service = new SeatsService(new SeatsRepository(session));
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            SeatsPK seatsPK = gson.fromJson(data, SeatsPK.class);
            var ent = seats.builder()
                    .seatsPK(seatsPK)
                    .fare_conditions(req.getParameter("fare_conditions"))
                    .build();
            service.create(ent);
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
            SeatsService service = new SeatsService(new SeatsRepository(session));
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            SeatsPK seatsPK = gson.fromJson(data, SeatsPK.class);
            service.delete(seatsPK);
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
            SeatsService service = new SeatsService(new SeatsRepository(session));
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            SeatsPK seatsPK = gson.fromJson(data, SeatsPK.class);
            var ent = seats.builder()
                    .seatsPK(seatsPK)
                    .fare_conditions(req.getParameter("fare_conditions"))
                    .build();
            service.update(ent);
            session.getTransaction().commit();
        }
    }
}
