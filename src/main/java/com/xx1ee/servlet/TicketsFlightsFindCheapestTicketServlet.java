package com.xx1ee.servlet;

import com.xx1ee.repos.TicketFlightsRepository;
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
import java.util.Objects;

@WebServlet("/ticketflights/findCheapestTicket")
public class TicketsFlightsFindCheapestTicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration configuration = new Configuration();
        configuration.configure();
        try(SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            TicketFlightsService ticketFlightsService = new TicketFlightsService(new TicketFlightsRepository(session));
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            reader.readLine();
            String line;
            while (!Objects.equals(line = reader.readLine(), "cityTo")) {
                sb.append(line);
            }
            String cityFrom = sb.toString();
            StringBuilder sb1 = new StringBuilder();
            String line1;
            while (!Objects.equals(line1 = reader.readLine(), null)) {
                sb1.append(line1);
            }
            String cityTo = sb1.toString();
            PrintWriter printWriter = resp.getWriter();
            var ticket = ticketFlightsService.findCheapestTicket(cityFrom, cityTo).get();
            printWriter.write(
                    """
                        %s %s %s %s    
                       """.formatted(ticket.getTicketFlightsId().getFlights(), ticket.getTicketFlightsId().getTickets(), ticket.getAmount(), ticket.getFare_conditions())
            );
            session.getTransaction().commit();
        }
    }
}
