package AppAvionets.java.AppAvionets.airports;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @PostMapping
    public ResponseEntity<AirportResponseDTO> addAirport (@RequestBody @Valid AirportRequestDTO airportRequestDTO){
        AirportResponseDTO airportResponseDTO = airportService.createAirport(airportRequestDTO);
        return new ResponseEntity<>(airportResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AirportResponseDTO>> getAllAirports() {
        List<AirportResponseDTO> airports = airportService.findAll();
        return new ResponseEntity<>(airports, HttpStatus.OK);
    }

    @GetMapping("/by-code/{codeAirport}")
    public ResponseEntity<AirportResponseDTO> getAirportByCode(@PathVariable("codeAirport") String codeAirport){
        AirportResponseDTO airportResponseDTO = airportService.findByCodeAirport(codeAirport);
        return new ResponseEntity<>(airportResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<AirportResponseDTO> getAirportById(@PathVariable Long id){
        AirportResponseDTO airportResponseDTO = airportService.findById(id);
        return new ResponseEntity<>(airportResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/by-id/{id}")
    public ResponseEntity<AirportResponseDTO> updateAirport(@PathVariable Long id, @RequestBody @Valid AirportRequestDTO airportRequestDTO){
        AirportResponseDTO airportResponseDTO = airportService.updateAirportById(id, airportRequestDTO);
        return new ResponseEntity<>(airportResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable Long id){
        airportService.deleteAirportById(id);
        return new ResponseEntity<>("The airport has been eliminated", HttpStatus.OK);
    }

}
