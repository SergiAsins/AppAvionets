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
    public void testCreateAirport_Succes() throws AirCompanyAlreadyExistsException{

        AirportRequestDTO airportRequestDTO = new AirportRequestDTO("Los Angeles Airport", "Los Angeles", "USA");

        when(airportRepository.findByName(airportRequestDTO.name())).thenReturn(Optional.empty());
        Airport
    }

}