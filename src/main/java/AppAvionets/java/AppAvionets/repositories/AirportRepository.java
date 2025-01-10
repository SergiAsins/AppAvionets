package AppAvionets.java.AppAvionets.repositories;

import AppAvionets.java.AppAvionets.entities.Airport;
import AppAvionets.java.AppAvionets.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}