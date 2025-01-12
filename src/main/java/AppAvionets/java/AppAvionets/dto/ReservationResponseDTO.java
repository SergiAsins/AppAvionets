package AppAvionets.java.AppAvionets.dto;

import java.sql.Timestamp;

public record ReservationResponseDTO(
        Long id,
        UserResponseDTO user,
        FlightResponseDTO flight,
        Timestamp ticketTime,
        Integer seats
) {
}
