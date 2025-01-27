package AppAvionets.java.AppAvionets.airports;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    Optional<Airport> findByName(String name);

    Optional<Airport> findById(Long id);

    Optional<Airport> findByCodeAirport(String codeAirport);
}