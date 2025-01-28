package AppAvionets.java.AppAvionets.reservations;

import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record ReservationRequestDTO(
        @NotNull(message = "the field Flight ID cannot be null")
        Long flightId,

        @NotNull(message = "the field seats cannot be null")
        Integer seats
) {
}
