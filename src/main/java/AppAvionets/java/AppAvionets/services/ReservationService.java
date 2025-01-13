package AppAvionets.java.AppAvionets.services;

import AppAvionets.java.AppAvionets.dto.ReservationRequestDTO;
import AppAvionets.java.AppAvionets.dto.ReservationResponseDTO;
import AppAvionets.java.AppAvionets.entities.Flight;
import AppAvionets.java.AppAvionets.entities.Reservation;
import AppAvionets.java.AppAvionets.entities.User;
import AppAvionets.java.AppAvionets.exceptions.AirCompanyNotFoundException;
import AppAvionets.java.AppAvionets.mappers.ReservationMapper;
import AppAvionets.java.AppAvionets.repositories.ReservationRepository;
import AppAvionets.java.AppAvionets.repositories.UserRepository;
import AppAvionets.java.AppAvionets.repositories.FlightRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;

    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository, FlightRepository flightRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
    }

    public ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO) {
        Optional<User> user = userRepository.findById(reservationRequestDTO.userId());
        Optional<Flight> flight = flightRepository.findById(reservationRequestDTO.flightId());
        // 24h previous booking guard:
        /*if (reservationRequestDTO.ticketTime().before(new Timestamp(System.currentTimeMillis() + 24 * 60 * 60 * 1000))) {
            throw new IllegalArgumentException("Reservations must be made at least 24 hours in advance.");
        }*/

        if (user.isEmpty()) {
            throw new AirCompanyNotFoundException("There is no User with this ID.");
        }

        if (flight.isEmpty()) {
            throw new AirCompanyNotFoundException("There is no Flight with this ID.");
        }

        Reservation reservation = ReservationMapper.toEntity(reservationRequestDTO, user.get(), flight.get());
        Reservation savedReservation = reservationRepository.save(reservation);
        return ReservationMapper.toResponseDto(savedReservation);
    }

    public List<ReservationResponseDTO> findAll() {
        List<Reservation> reservationList = reservationRepository.findAll();

        if (reservationList.isEmpty()) {
            throw new AirCompanyNotFoundException("There are no reservations to show.");
        }

        return reservationList.stream()
                .map(ReservationMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public ReservationResponseDTO findById(Long id){
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        if (optionalReservation.isEmpty()) {
            throw new AirCompanyNotFoundException("The Reservation with the id" + id + "does not exist.");
        }
        Reservation reservation = optionalReservation.get();
        return ReservationMapper.toResponseDto(reservation);
    }

    public ReservationResponseDTO updateReservationById(Long id, ReservationRequestDTO reservationRequestDTO){
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isEmpty()){
            throw new AirCompanyNotFoundException("The Reservation with the id" + id + "does not exist.");
        }
        Reservation reservation = optionalReservation.get();

        Optional<User> optionalUser = userRepository.findById(reservationRequestDTO.userId());
        if(optionalUser.isEmpty()){
            throw new AirCompanyNotFoundException(("The User with the id" + reservationRequestDTO.userId() + "does not exist."));
        }

        Optional<Flight> optionalFlight = flightRepository.findById(reservationRequestDTO.flightId());
        if(optionalFlight.isEmpty()){
            throw new AirCompanyNotFoundException(("The Flight with the id" + reservationRequestDTO.flightId() + "does not exist."));
        }

        reservation.setTicketTime(reservationRequestDTO.ticketTime());
        reservation.setSeats(reservationRequestDTO.seats());

        Reservation updatedReservation = reservationRepository.save(reservation);

        return ReservationMapper.toResponseDto(updatedReservation);
    }

    public void deleteReservationById(Long id){
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isEmpty()){
            throw new AirCompanyNotFoundException(("The Reservation with the id" + id + "does not exist."));
        }
        reservationRepository.deleteById(id);
    }

    public List<ReservationResponseDTO> findFutureReservations(Long userId){
        Timestamp actualDateTime = new Timestamp(System.currentTimeMillis());
        List<Reservation> reservations = reservationRepository.findFutureReservations(userId, actualDateTime);
        return reservations.stream().map(ReservationMapper::toResponseDto).collect(Collectors.toList());
    }

    public List<ReservationResponseDTO> findPastReservations(Long userId){
        Timestamp actualDateTime = new Timestamp(System.currentTimeMillis());
        List<Reservation> reservations = reservationRepository.findPastReservations(userId, actualDateTime);
        return reservations.stream().map(ReservationMapper::toResponseDto).collect(Collectors.toList());
    }
}
