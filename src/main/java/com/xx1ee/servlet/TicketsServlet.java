package com.xx1ee.servlet;

import com.google.gson.Gson;
import com.xx1ee.classes.ContactData;
import com.xx1ee.classes.SeatsPK;
import com.xx1ee.entity.bookings;
import com.xx1ee.entity.seats;
import com.xx1ee.entity.tickets;
import com.xx1ee.repos.BookingsRepository;
import com.xx1ee.repos.SeatsRepository;
import com.xx1ee.repos.TicketsRepository;
import com.xx1ee.service.BookingsService;
import com.xx1ee.service.SeatsService;
import com.xx1ee.service.TicketsService;
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

@WebServlet("/tickets")
public class TicketsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            TicketsService ticketsService = new TicketsService(new TicketsRepository(session));
            PrintWriter printWriter = resp.getWriter();
            ticketsService.findAll().forEach(s -> printWriter.write(
                    """
                        %s %s %s %s %s %s
                       """.formatted(s.getTicket_no(), s.getBook_ref().getBook_ref(), s.getPassenger_id(), s.getPassenger_name(), s.getContact_data().getEmail(), s.getContact_data().getPhone())
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
            TicketsService ticketsService = new TicketsService(new TicketsRepository(session));
            BookingsService bookingsService = new BookingsService(new BookingsRepository(session));
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            ContactData contactData = gson.fromJson(data, ContactData.class);
            var ent = tickets.builder()
                    .ticket_no(req.getParameter("ticket_no"))
                    .passenger_id(req.getParameter("passenger_id"))
                    .passenger_name(req.getParameter("passenger_name"))
                    .book_ref(bookingsService.findById(req.getParameter("book_ref")).orElse(new bookings()))
                    .contact_data(contactData)
                    .build();
            ticketsService.create(ent);
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
            TicketsService ticketsService = new TicketsService(new TicketsRepository(session));
            ticketsService.delete(req.getParameter("ticket_no"));
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
            TicketsService ticketsService = new TicketsService(new TicketsRepository(session));
            BookingsService bookingsService = new BookingsService(new BookingsRepository(session));
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            ContactData contactData = gson.fromJson(data, ContactData.class);
            var ent = tickets.builder()
                    .ticket_no(req.getParameter("ticket_no"))
                    .passenger_id(req.getParameter("passenger_id"))
                    .passenger_name(req.getParameter("passenger_name"))
                    .book_ref(bookingsService.findById(req.getParameter("book_ref")).orElse(new bookings()))
                    .contact_data(contactData)
                    .build();
            ticketsService.update(ent);
            session.getTransaction().commit();
        }
    }
}
