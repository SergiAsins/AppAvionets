package AppAvionets.java.AppAvionets.reservations;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> addReservation(@RequestBody @Valid ReservationRequestDTO reservationRequestDTO){
        ReservationResponseDTO reservationResponseDTO = reservationService.createReservation(reservationRequestDTO);
        return new ResponseEntity<>(reservationResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservations(){
        List<ReservationResponseDTO> reservationList = reservationService.findAll();
        return new ResponseEntity<>(reservationList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id){
        ReservationResponseDTO reservationResponseDTO = reservationService.findById(id);
        return new ResponseEntity<>(reservationResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> updateReservationById(@PathVariable Long id, @RequestBody @Valid ReservationRequestDTO reservationRequestDTO){
        ReservationResponseDTO reservationResponseDTO = reservationService.updateReservationById(id, reservationRequestDTO);
        return new ResponseEntity<>(reservationResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservationById(@PathVariable Long id){
        reservationService.deleteReservationById(id);
        return new ResponseEntity<>("The reservation has been deleted.", HttpStatus.OK);

    }

    @GetMapping("/my-reservations")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationsByAuthenticatedUser(){
        List<ReservationResponseDTO> userReservations = reservationService.getReservationsByAuthenticatedIdUser();
        return new ResponseEntity<>(userReservations, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationsByUserId(@PathVariable Long userId){
        List<ReservationResponseDTO> userReservations = reservationService.getReservationByUserId(userId);
        return new ResponseEntity<>(userReservations, HttpStatus.OK);
    }
}