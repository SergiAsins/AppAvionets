package AppAvionets.java.AppAvionets.mappers;

import AppAvionets.java.AppAvionets.dto.FlightRequestDTO;
import AppAvionets.java.AppAvionets.dto.FlightResponseDTO;
import AppAvionets.java.AppAvionets.entities.Airport;
import AppAvionets.java.AppAvionets.entities.Flight;

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
