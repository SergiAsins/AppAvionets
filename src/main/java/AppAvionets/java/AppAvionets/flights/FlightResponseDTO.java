package AppAvionets.java.AppAvionets.flights;

import AppAvionets.java.AppAvionets.airports.AirportResponseDTO;

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
