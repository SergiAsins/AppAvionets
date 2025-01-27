package AppAvionets.java.AppAvionets.flights;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
  @Query("SELECT f FROM Flight f WHERE f.origin.codeAirport = :originCode AND f.destination.codeAirport = :destinationCode AND f.departureTime BETWEEN :startOfDay AND :endOfDay AND f.availableSeats >= :seats")
  List<Flight> findFlightsByCriteria(
          @Param("originCode") String originCode,
          @Param("destinationCode") String destinationCode,
          @Param("startOfDay") Timestamp startOfDay,
          @Param("endOfDay") Timestamp endOfDay,
          @Param("seats") Integer seats);

  Optional<Flight> findByFlightNumberAndOrigin_id(String flightNumber, Long airportDestinationId);


}