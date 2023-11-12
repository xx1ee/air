package com.xx1ee.servlet;

import com.google.gson.Gson;
import com.xx1ee.classes.BoardingPassesPK;
import com.xx1ee.classes.Model;
import com.xx1ee.dto.BoardingPassesCreateDto;
import com.xx1ee.entity.aircrafts_data;
import com.xx1ee.entity.boarding_passes;
import com.xx1ee.entity.flights;
import com.xx1ee.mapper.BoardingPassesCreateMapper;
import com.xx1ee.mapper.BoardingPassesReadMapper;
import com.xx1ee.repos.AircraftsDataRepository;
import com.xx1ee.repos.BoardingPassesRepository;
import com.xx1ee.service.AircraftsDataService;
import com.xx1ee.service.BoardingPassesService;
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

@WebServlet("/boardingpasses")
public class BoardingPassesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            BoardingPassesService boardingPassesService = new BoardingPassesService(new BoardingPassesRepository(session), new BoardingPassesCreateMapper(), new BoardingPassesReadMapper());
            PrintWriter printWriter = resp.getWriter();
            boardingPassesService.findAll().forEach(bp -> printWriter.write(
                    """
                        %s %s %s %s %s %s      
                       """.formatted(bp.getBoardingPassesPK().getFlight_id(), bp.getBoardingPassesPK().getTicket_no(),
                            bp.getBoarding_no(), bp.getSeat_no(), bp.getTicket_flights().getFare_conditions(), bp.getTicket_flights().getAmount())
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
            BoardingPassesService boardingPassesService = new BoardingPassesService(new BoardingPassesRepository(session), new BoardingPassesCreateMapper(), new BoardingPassesReadMapper());
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            BoardingPassesPK boardingPassesPK = gson.fromJson(data, BoardingPassesPK.class);
            var ent = new BoardingPassesCreateDto(boardingPassesPK, Integer.valueOf(req.getParameter("boarding_no")), req.getParameter("seat_no"));
            boardingPassesService.save(ent);
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
            BoardingPassesService boardingPassesService = new BoardingPassesService(new BoardingPassesRepository(session), new BoardingPassesCreateMapper(), new BoardingPassesReadMapper());
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            BoardingPassesPK boardingPassesPK = gson.fromJson(data, BoardingPassesPK.class);
            boardingPassesService.delete(boardingPassesPK);
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
            BoardingPassesService boardingPassesService = new BoardingPassesService(new BoardingPassesRepository(session), new BoardingPassesCreateMapper(), new BoardingPassesReadMapper());
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            BoardingPassesPK boardingPassesPK = gson.fromJson(data, BoardingPassesPK.class);
            var ent = boarding_passes.builder()
                    .boardingPassesPK(boardingPassesPK)
                    .boarding_no(Integer.valueOf(req.getParameter("boarding_no")))
                    .seat_no(req.getParameter("seat_no"))
                    .build();
            boardingPassesService.update(ent);
            session.getTransaction().commit();
        }
    }
}
