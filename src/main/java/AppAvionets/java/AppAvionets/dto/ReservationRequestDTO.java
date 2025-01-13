package AppAvionets.java.AppAvionets.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record ReservationRequestDTO(
        @NotNull(message = "the field User ID cannot be null")
        Long userId,

        @NotNull(message = "the field Flight ID cannot be null")
        Long flightId,

        @NotNull(message = "the field date cannot be null")
        //@Future(message = "The date cannot be previous to actual date")
        Timestamp ticketTime,

        @NotNull(message = "the field date cannot be null")
        Integer seats
) {
}
