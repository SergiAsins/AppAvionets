package AppAvionets.java.AppAvionets.flights;

import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping()
    public ResponseEntity<List<FlightResponseDTO>> getAllFlights(){
        List<FlightResponseDTO> allFlights = flightService.listAllFlights();
        return new ResponseEntity<>(allFlights, HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<FlightResponseDTO> getFLightById(@PathVariable Long id){
        FlightResponseDTO flightResponseDTO = flightService.showFlightById(id);
        return new ResponseEntity<>(flightResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightResponseDTO>> searchFlights(
            @RequestParam String originCodeAirport,
            @RequestParam String destinationCodeAirport,
            @RequestParam String date,
            @RequestParam Integer seats){

        // convert the date to a TimeStamp
        Timestamp timestamp;
        try {
            String formattedDate = date.replace("T", " ").split("\\.")[0];
            timestamp = Timestamp.valueOf(formattedDate);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid date format. Use 'YYYY-MM-DDTHH:mm:ss' (e.g., 2026-01-25T16:30:00)");
        }

        List <FlightResponseDTO> flights = flightService.searchFlights(originCodeAirport, destinationCodeAirport, timestamp, seats);
        return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FlightResponseDTO> createFlight(@RequestBody @Valid FlightRequestDTO flightRequestDTO){
        FlightResponseDTO flight = flightService.createFlight(flightRequestDTO);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> updateFlight(@PathVariable Long id, @RequestBody @Valid FlightRequestDTO flightRequestDTO){
        FlightResponseDTO modifiedFlight = flightService.modifyFlight(id, flightRequestDTO);
        return new ResponseEntity<>(modifiedFlight, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> deleteFlight(@PathVariable Long id){
        FlightResponseDTO erasedFlight = flightService.deleteFlightById(id);
        return new ResponseEntity<> (erasedFlight, HttpStatus.OK);
    }

}
