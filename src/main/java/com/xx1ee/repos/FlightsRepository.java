package com.xx1ee.repos;

import com.xx1ee.dto.PopularPlaneReadDto;
import com.xx1ee.entity.flights;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class FlightsRepository implements Repository<Integer, flights> {
    private final Session session;
    @Override
    public flights save(flights entity) {
        session.persist(entity);
        return entity;
    }

    @Override
    public void delete(flights id) {
        session.delete(id);
    }

    @Override
    public void update(flights entity) {
        session.merge(entity);
    }

    @Override
    public Optional<flights> findById(Integer id) {
        return Optional.of(session.find(flights.class, id));
    }

    @Override
    public List<flights> findAll() {
        return session.createNativeQuery("select * from bookings.flights", flights.class).getResultList();
    }
    public flights shortestFlight() {
        return session.createNativeQuery("select flight_no, aircraft_code, departure_airport, arrival_airport,scheduled_arrival, scheduled_departure, status, (scheduled_arrival - scheduled_departure) as act " +
                "from flights order by act asc", flights.class).getResultList().get(0);
    }
    public PopularPlaneReadDto mostPopularAircraft() {
        return session.createNativeQuery("select flights.aircraft_code, ad.model, count(flights.aircraft_code) from flights join aircrafts_data ad on " +
                "flights.aircraft_code = ad.aircraft_code " +
                "group by flights.aircraft_code, ad.model " +
                "order by count(flights.aircraft_code) desc", PopularPlaneReadDto.class).getResultList().get(0);
    }
}
