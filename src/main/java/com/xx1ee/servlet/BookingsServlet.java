package com.xx1ee.servlet;

import com.google.gson.Gson;
import com.xx1ee.classes.ContactData;
import com.xx1ee.classes.OffsetDateTimeCreate;
import com.xx1ee.entity.bookings;
import com.xx1ee.entity.tickets;
import com.xx1ee.repos.BookingsRepository;
import com.xx1ee.repos.TicketsRepository;
import com.xx1ee.service.BookingsService;
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
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@WebServlet("/bookings")
public class BookingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            BookingsService bookingsService = new BookingsService(new BookingsRepository(session));
            PrintWriter printWriter = resp.getWriter();
            bookingsService.findAll().forEach(bookings -> printWriter.write(
                    """
                        %s %s %s %s
                       """.formatted(bookings.getBook_ref(), bookings.getBook_date().toString(), bookings.getTotal_amount(),
                            bookings.getTickets().stream().map(tickets -> tickets.getTicket_no() + " " + tickets.getContact_data().getPhone() + " " + tickets.getPassenger_id()).toList())
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
            BookingsService bookingsService = new BookingsService(new BookingsRepository(session));
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            OffsetDateTimeCreate offsetDateTimeCreate = gson.fromJson(data, OffsetDateTimeCreate.class);
            var ent = bookings.builder().book_ref(req.getParameter("book_ref")).book_date(OffsetDateTime.of(offsetDateTimeCreate.getYear(), offsetDateTimeCreate.getMonth()
            , offsetDateTimeCreate.getDayOfMonth(), offsetDateTimeCreate.getHour(), offsetDateTimeCreate.getMinute(), offsetDateTimeCreate.getSecond(),
                    offsetDateTimeCreate.getNanoOfSecond(), ZoneOffset.ofHours(offsetDateTimeCreate.getOffset()))).total_amount(Long.parseLong(req.getParameter("total amount"))).build();
            bookingsService.create(ent);
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
            BookingsService bookingsService = new BookingsService(new BookingsRepository(session));
            bookingsService.delete(req.getParameter("book_ref"));
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
            BookingsService bookingsService = new BookingsService(new BookingsRepository(session));
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String data = sb.toString();
            Gson gson = new Gson();
            OffsetDateTimeCreate offsetDateTimeCreate = gson.fromJson(data, OffsetDateTimeCreate.class);
            var ent = bookings.builder().book_ref(req.getParameter("book_ref")).book_date(OffsetDateTime.of(offsetDateTimeCreate.getYear(), offsetDateTimeCreate.getMonth()
                    , offsetDateTimeCreate.getDayOfMonth(), offsetDateTimeCreate.getHour(), offsetDateTimeCreate.getMinute(), offsetDateTimeCreate.getSecond(),
                    offsetDateTimeCreate.getNanoOfSecond(), ZoneOffset.ofHours(offsetDateTimeCreate.getOffset()))).total_amount(Long.parseLong(req.getParameter("total amount"))).build();
            bookingsService.update(ent);
            session.getTransaction().commit();
        }
    }
}
