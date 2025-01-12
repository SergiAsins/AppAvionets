package AppAvionets.java.AppAvionets.dto;

import AppAvionets.java.AppAvionets.entities.Airport;

import java.sql.Timestamp;

public record FlightResponseDTO(
    Long id,
    String flightNumber,
    Boolean Status,
    AirportResponseDTO airportOrigin,
    AirportResponseDTO airportDestination,
    Timestamp departureTime,
    Timestamp arrivalTime,
    Integer availableSeats
){
}
