package AppAvionets.java.AppAvionets.repositories;

import AppAvionets.java.AppAvionets.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
  Optional<Flight> findByFlightNumberAndOrigin_id(String flightNumber, Long airportDestinationId);
}