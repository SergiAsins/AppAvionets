package AppAvionets.java.AppAvionets.controllers;

import AppAvionets.java.AppAvionets.dto.ReservationRequestDTO;
import AppAvionets.java.AppAvionets.dto.ReservationResponseDTO;
import AppAvionets.java.AppAvionets.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
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
    public ResponseEntity<ReservationResponseDTO> getReservationById(Long id){
        ReservationResponseDTO reservationResponseDTO = reservationService.findById(id);
        return new ResponseEntity<>(reservationResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/next/{userId}")
    public ResponseEntity<List<ReservationResponseDTO>> getFutureReservationsByUserId(@RequestParam Long userId){
        List<ReservationResponseDTO> reservationList = reservationService.findFutureReservations(userId);
        return new ResponseEntity<>(reservationList, HttpStatus.OK);
    }

    @GetMapping("/past/{userId}")
    public ResponseEntity<List<ReservationResponseDTO>> getPastReservationsByUserId(@RequestParam Long userId){
        List<ReservationResponseDTO> reservationList = reservationService.findPastReservations(userId);
        return new ResponseEntity<>(reservationList, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> updateReservationById(@RequestParam Long id, @RequestBody @Valid ReservationRequestDTO reservationRequestDTO){
        ReservationResponseDTO reservationResponseDTO = reservationService.updateReservationById(id, reservationRequestDTO);
        return new ResponseEntity<>(reservationResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservationById(@PathVariable Long id){
        reservationService.deleteReservationById(id);
        return new ResponseEntity<>("The reservation has been deleted.", HttpStatus.OK);

    }
}