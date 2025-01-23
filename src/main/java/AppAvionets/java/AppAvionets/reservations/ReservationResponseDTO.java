package AppAvionets.java.AppAvionets.reservations;

import AppAvionets.java.AppAvionets.flights.FlightResponseDTO;
import AppAvionets.java.AppAvionets.users.UserResponseDTO;

import java.sql.Timestamp;

public record ReservationResponseDTO(
        Long id,
        UserResponseDTO user,
        FlightResponseDTO flight,
        Timestamp ticketTime,
        Integer seats
) {
}
