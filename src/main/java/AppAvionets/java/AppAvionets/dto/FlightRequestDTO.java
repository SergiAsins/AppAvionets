package AppAvionets.java.AppAvionets.dto;

import AppAvionets.java.AppAvionets.entities.Airport;
import AppAvionets.java.AppAvionets.entities.Flight;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record FlightRequestDTO (

        @NotNull(message = "The flightNumber cannot be null")
        @NotEmpty(message = "The flightNumber cannot be empty")
        String flightNumber,

        Boolean status, //try without

        @NotNull(message = "The AirportOrigin ID cannot be null")
        Long airportOriginId,

        @NotNull(message = "The AirportDestination ID cannot be null")
        Long airportDestinationId,

        @NotNull(message = "The departure time cannot be null")
        Timestamp departureTime,

        @NotNull(message = "The arrival time cannot be null")
        Timestamp arrivalTime,

        @NotNull(message = "The available seats cannot be null")
        Integer availableSeats
){
}
