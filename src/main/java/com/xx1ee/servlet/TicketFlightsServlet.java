package com.xx1ee.servlet;

import com.google.gson.Gson;
import com.xx1ee.classes.BoardingPassesPK;
import com.xx1ee.classes.TicketFlightsId;
import com.xx1ee.dto.BoardingPassesCreateDto;
import com.xx1ee.entity.ticket_flights;
import com.xx1ee.mapper.BoardingPassesCreateMapper;
import com.xx1ee.mapper.BoardingPassesReadMapper;
import com.xx1ee.repos.BoardingPassesRepository;
import com.xx1ee.repos.TicketFlightsRepository;
import com.xx1ee.service.BoardingPassesService;
import com.xx1ee.service.TicketFlightsService;
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

@WebServlet("/ticketflights")
public class TicketFlightsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            TicketFlightsService ticketFlightsService = new TicketFlightsService(new TicketFlightsRepository(session));
            PrintWriter printWriter = resp.getWriter();
            ticketFlightsService.findAll().forEach(bp -> printWriter.write(
                    """
                        %s %s %s %s    
                       """.formatted(bp.getTicketFlightsId().getFlights().getFlight_id(), bp.getTicketFlightsId().getTickets().getTicket_no(),
                            bp.getFare_conditions(), bp.getAmount())
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
            TicketFlightsService ticketFlightsService = new TicketFlightsService(new TicketFlightsRepository(session));
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            TicketFlightsId t = gson.fromJson(data, TicketFlightsId.class);
            var ent = ticket_flights.builder()
                    .ticketFlightsId(t)
                    .amount(Long.valueOf(req.getParameter("amount")))
                    .fare_conditions(req.getParameter("fare_conditions"))
                    .build();
            ticketFlightsService.create(ent);
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
            TicketFlightsService ticketFlightsService = new TicketFlightsService(new TicketFlightsRepository(session));
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            TicketFlightsId t = gson.fromJson(data, TicketFlightsId.class);
            var ent = ticket_flights.builder()
                    .ticketFlightsId(t)
                    .amount(Long.valueOf(req.getParameter("amount")))
                    .fare_conditions(req.getParameter("fare_conditions"))
                    .build();
            ticketFlightsService.update(ent);
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
            TicketFlightsService ticketFlightsService = new TicketFlightsService(new TicketFlightsRepository(session));
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            TicketFlightsId t = gson.fromJson(data, TicketFlightsId.class);
            ticketFlightsService.delete(t);
            session.getTransaction().commit();
        }
    }
}
