package AppAvionets.java.AppAvionets.airports;

import static org.junit.jupiter.api.Assertions.*;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;
import AppAvionets.java.AppAvionets.exceptions.AirCompanyAlreadyExistsException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@Sql(scripts = "/sql/cleanup.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;


    @Test
    public void testFindAllAirports(){

        List<Airport> airports = Arrays.asList(
                new Airport("Los Angeles Airport", "Los Angeles", "USA"),
                new Airport("Wasabi Airport", "Tokyo", "Japan")
        );
        when(airportRepository.findAll()).thenReturn(airports);


        List<AirportResponseDTO> responses = airportService.findAll();

        assertEquals(responses.size(), airports.size());
        assertEquals(responses.get(0).name(), airports.get(0).getName());
    }

    @Test
    public void testFindAirportById_Success(){

        Long airportId = 1L;
        Airport airport = new Airport("Los Angeles Airport", "Los Angeles", "USA");
        when(airportRepository.findById(airportId)).thenReturn(Optional.of(airport));

        AirportResponseDTO airportResponseDTO = airportService.findById(airportId);

        assertEquals(airportResponseDTO.id(), airport.getId());
        assertEquals(airportResponseDTO.name(), airport.getName());
    }

    @Test
    public void testFindAirportByName_Success(){

        String airportName = "Los Angeles Airport";
        Airport airport = new Airport("Los Angeles Airport", "Los Angeles", "USA");
        when(airportRepository.findByName(airportName)).thenReturn(Optional.of(airport));

        List<AirportResponseDTO> listResponsesDTOs = airportService.findByName(airportName);

        assertEquals(listResponsesDTOs.size(), 1);

    }
}