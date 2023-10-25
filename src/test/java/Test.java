import com.xx1ee.entity.aircrafts_data;
import com.xx1ee.entity.airports_data;
import com.xx1ee.entity.tickets;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {
    @org.junit.jupiter.api.Test
    void test1() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            tickets e = session.createNativeQuery("SELECT * FROM tickets  " +
                    "e WHERE e.ticket_no = '0005432000861'", tickets.class).getSingleResult();
            System.out.println(e.getContact_data().getEmail());
            System.out.println(e.getContact_data().getPhone());
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test2() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            aircrafts_data e = session.createNativeQuery("SELECT * FROM aircrafts_data  " +
                    "e WHERE e.aircraft_code = '319'", aircrafts_data.class).getSingleResult();
            System.out.println(e.getRange());
            System.out.println(e.getModel().getEn());
            System.out.println(e.getModel().getRu());
            session.getTransaction().commit();
        }
    }
    @org.junit.jupiter.api.Test
    void test3() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            airports_data e = session.createNativeQuery("SELECT * FROM airports_data  " +
                    "e WHERE e.airport_code = 'BZK'", airports_data.class).getSingleResult();
            System.out.println(e.getAirport_code());
            System.out.println(e.getAirport_name().getEn());
            System.out.println(e.getCoordinates());
            System.out.println(e.getTimezone());
            System.out.println(e.getDepartureList().get(0).getAircraft_code());
            System.out.println(e.getDepartureList().get(0).getActual_departure());
            System.out.println(e.getDepartureList().get(0).getStatus());
            System.out.println(e.getCity().getEn() + " " + e.getCity().getRu());
            session.getTransaction().commit();
        }
    }
}
