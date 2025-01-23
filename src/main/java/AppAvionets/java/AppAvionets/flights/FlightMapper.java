package AppAvionets.java.AppAvionets.flights;

import AppAvionets.java.AppAvionets.airports.AirportMapper;
import AppAvionets.java.AppAvionets.airports.Airport;

public class FlightMapper {
    public static Flight toEntity(FlightRequestDTO flightRequestDTO, Airport airportOrigin, Airport airportDestination) {
        return new Flight(
                flightRequestDTO.flightNumber(),
                flightRequestDTO.status(),
                airportOrigin,
                airportDestination,
                flightRequestDTO.departureTime(),
                flightRequestDTO.arrivalTime(),
                flightRequestDTO.availableSeats()
        );
    }

    public static FlightResponseDTO toResponseDTO(Flight flight) {
        return new FlightResponseDTO(
                flight.getIdFlight(),
                flight.getFlightNumber(),
                flight.getStatus(),
                AirportMapper.toResponseDto(flight.getOrigin()),
                AirportMapper.toResponseDto(flight.getDestination()),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getAvailableSeats()
        );
    }
}
