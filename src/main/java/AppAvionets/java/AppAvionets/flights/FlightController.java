package AppAvionets.java.AppAvionets.flights;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> getFLightById(@PathVariable Long id){
        FlightResponseDTO flightResponseDTO = flightService.showFlightById(id);
        return new ResponseEntity<>(flightResponseDTO, HttpStatus.OK);
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
