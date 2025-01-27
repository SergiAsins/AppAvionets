package AppAvionets.java.AppAvionets.flights;

import static org.junit.jupiter.api.Assertions.*;

import AppAvionets.java.AppAvionets.airports.Airport;
import AppAvionets.java.AppAvionets.airports.AirportResponseDTO;
import AppAvionets.java.AppAvionets.airports.AirportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    FlightRepository flightRepository;

    @Mock
    AirportRepository airportRepository;

    @InjectMocks
    private FlightService flightService;

    @Test // Unit test
    void testReserveSeats_Success() {
        Flight flight = new Flight("FL123", true,
                new Airport("JDC", "Hartsfield–Jackson Atlanta International Airport", "Atlanta", "United States"),
                new Airport("UAE", "Dubai International Airport", "Dubai", "United Arab Emirates"),
                Timestamp.valueOf("2025-01-30 10:00:00"), Timestamp.valueOf("2025-01-30 14:00:00"), 100);

        flight.reserveSeats(10);

        assertEquals(90, flight.getAvailableSeats());
        assertTrue(flight.getStatus());
    }

    @Test // Unit test
    void testValidateFlightTimes_InvalidTimes() {
        Flight flight = new Flight("AA123", true,
                new Airport("DLL", "Dallas Fort Worth International Airport", "Dallas", "United States"),
                new Airport("TKW", "Tokyo Haneda Airport", "Tokyo", "Japan"),
                Timestamp.valueOf("2025-01-30 14:00:00"), Timestamp.valueOf("2025-01-30 10:00:00"), 100);

        assertThrows(IllegalStateException.class, flight::validateFlightTimes);
    }

    @Test // Integration test
    void testCreateFlight_Success() {
        FlightRequestDTO flightRequestDTO = new FlightRequestDTO("FL123", true, 1L, 2L,
                Timestamp.valueOf("2025-01-30 10:00:00"), Timestamp.valueOf("2025-01-30 14:00:00"), 100);

        when(airportRepository.findById(1L)).thenReturn(Optional.of(new Airport("IST", "Istanbul Airport", "Istanbul", "Turkey")));
        when(airportRepository.findById(2L)).thenReturn(Optional.of(new Airport("LAA", "Los Angeles International Airport", "LA", "USA")));
        when(flightRepository.save(any(Flight.class))).thenAnswer(invocation -> invocation.getArgument(0));

        FlightResponseDTO flightResponseDTO = flightService.createFlight(flightRequestDTO);

        assertEquals("FL123", flightResponseDTO.flightNumber());
        assertEquals("Istanbul", flightResponseDTO.airportOrigin().city());
        assertEquals("LA", flightResponseDTO.airportDestination().city());
    }

    @Test // Functional test
    void testGetAllFlights() {
        List<FlightResponseDTO> flights = Arrays.asList(
                new FlightResponseDTO(1L, "FL123", true,
                        new AirportResponseDTO(1L, "IST", "Istanbul Airport", "Istanbul", "Turkey"),
                        new AirportResponseDTO(2L, "LAA","Los Angeles International Airport", "LA", "USA"),
                        Timestamp.valueOf("2025-01-24 16:30:00"), Timestamp.valueOf("2025-01-30 14:00:00"), 100)
        );

        when(flightRepository.findAll()).thenReturn(Arrays.asList(
                new Flight("FL123", true,
                        new Airport("IST", "Istanbul Airport", "Istanbul", "Turkey"),
                        new Airport("LAA", "Los Angeles International Airport", "LA", "USA"),
                        Timestamp.valueOf("2025-01-24 16:30:00"), Timestamp.valueOf("2025-01-30 14:00:00"), 100)
        ));

        List<FlightResponseDTO> result = flightService.listAllFlights();

        assertEquals(flights.get(0).flightNumber(), result.get(0).flightNumber());
    }
}
