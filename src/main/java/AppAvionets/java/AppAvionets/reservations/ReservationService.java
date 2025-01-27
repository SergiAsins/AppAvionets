package AppAvionets.java.AppAvionets.reservations;

import AppAvionets.java.AppAvionets.flights.Flight;
import AppAvionets.java.AppAvionets.users.User;
import AppAvionets.java.AppAvionets.exceptions.AirCompanyNotFoundException;
import AppAvionets.java.AppAvionets.users.UserRepository;
import AppAvionets.java.AppAvionets.flights.FlightRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.time.Instant;
import java.util.concurrent.Executors;

//handle status reset after a determinate time
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

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

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO) {
        Optional<User> user = userRepository.findById(reservationRequestDTO.userId());
        Optional<Flight> optionalFlight = flightRepository.findById(reservationRequestDTO.flightId());

        if (user.isEmpty()) {
            throw new AirCompanyNotFoundException("There is no User with this ID.");
        }

        if (optionalFlight.isEmpty()) {
            throw new AirCompanyNotFoundException("There is no Flight with this ID.");
        }

        Flight flight = optionalFlight.get();

        if(reservationRequestDTO.seats() > flight.getAvailableSeats()){
            throw new IllegalArgumentException("Not enough seats available for this Flight.");
        };

        //Create and save Reservation
        Reservation reservation = ReservationMapper.toEntity(reservationRequestDTO, user.get(), optionalFlight.get());
        // Save reservation and update flight status
        Reservation savedReservation = reservationRepository.save(reservation);

        // Update flight status and schedule reset
        flight.setStatus(false);
        flight.reserveSeats(reservationRequestDTO.seats());
        flightRepository.save(flight);

        // Schedule a task to revert the status after 15 sec
        scheduler.schedule(() -> {
            flight.setStatus(flight.getAvailableSeats() > 0);
            flightRepository.save(flight);
        }, 15, TimeUnit.SECONDS);

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
