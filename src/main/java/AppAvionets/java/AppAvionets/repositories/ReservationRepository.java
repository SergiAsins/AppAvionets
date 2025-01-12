package AppAvionets.java.AppAvionets.repositories;

import AppAvionets.java.AppAvionets.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
  @Query("SELECT a FROM Reservation a WHERE a.flight.id = :flightId AND a.ticketTime > :timestamp")
  List<Reservation> findFutureReservations(@Param("flightId") Long flightId, @Param("timestamp") Timestamp timestamp);

  @Query("SELECT a FROM Reservation a WHERE a.flight.id = :flightId AND a.ticketTime < :timestamp")
  List<Reservation> findPastReservations(@Param("flightId") Long flightId, @Param("timestamp") Timestamp timestamp);
}