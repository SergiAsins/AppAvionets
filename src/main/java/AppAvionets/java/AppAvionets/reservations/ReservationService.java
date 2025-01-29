package AppAvionets.java.AppAvionets.reservations;

import AppAvionets.java.AppAvionets.exceptions.flights.AirCompanyErrorFlightException;
import AppAvionets.java.AppAvionets.exceptions.reservations.AirCompanyReservationErrorException;
import AppAvionets.java.AppAvionets.flights.Flight;
import AppAvionets.java.AppAvionets.users.User;
import AppAvionets.java.AppAvionets.exceptions.general.AirCompanyNotFoundException;
import AppAvionets.java.AppAvionets.users.UserRepository;
import AppAvionets.java.AppAvionets.flights.FlightRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.concurrent.Executors;

import org.springframework.security.core.context.SecurityContextHolder;

//handle status reset after a determinate time


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
        //get authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        //Search the authenticated user in the DB
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){
            throw new AirCompanyNotFoundException("Authenticated user not found in the database.");
        }
        User user = userOptional.get();


        Optional<Flight> optionalFlight = flightRepository.findById(reservationRequestDTO.flightId());
        if (optionalFlight.isEmpty()) {
            throw new AirCompanyNotFoundException("There is no Flight with this ID.");
        }

        Flight flight = optionalFlight.get();

        if(reservationRequestDTO.seats() > flight.getAvailableSeats()){
            throw new IllegalArgumentException("Not enough seats available for this Flight.");
        };

        //Create and save Reservation
        Reservation reservation = ReservationMapper.toEntity(reservationRequestDTO, user, flight);
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

        //get authenticated user:
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty()){
            throw new AirCompanyNotFoundException(("Authenticated user not found in the database."));
        }

        User authenticatedUser = optionalUser.get();

        //verifies that the authenticated user is the owner of the Reservation:
        if (!reservation.getUser().getId().equals(authenticatedUser.getId())) {
            throw new AirCompanyReservationErrorException("You cannot update a reservation that does not belong to you.");
        }

        Optional<Flight> optionalFlight = flightRepository.findById(reservationRequestDTO.flightId());
        if(optionalFlight.isEmpty()){
            throw new AirCompanyNotFoundException(("The Flight with the id" + reservationRequestDTO.flightId() + "does not exist."));
        }

        Flight flight = optionalFlight.get();
        if (!reservation.getFlight().getIdFlight().equals(flight.getIdFlight())) {
            throw new AirCompanyErrorFlightException("Cannot change the flight associated with an existing reservation.");
        }

        //update the availableSeats in the flight
        updateFlightAvailableSeats(reservation, reservationRequestDTO.seats());

        // updates the reservation
        reservation.setSeats(reservationRequestDTO.seats());
        reservation.setTicketTime(reservation.getTicketTime());
        reservation.setUser(optionalUser.get());
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

    public void updateFlightAvailableSeats(Reservation reservation, int newSeats){
        Flight flight = reservation.getFlight();

        //adjusts the number of availableSeats in the flight:
        int previousSeats = reservation.getSeats();
        int seatDifference = newSeats - previousSeats;

        if (flight.getAvailableSeats() - seatDifference < 0) {
            throw new AirCompanyErrorFlightException("Not enough available seats to update the reservation.");
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - seatDifference);

        //temporally changes the flight status into false
        flight.setStatus(false);
        flightRepository.save(flight);

        //restores the flight status after 15sec:
        scheduler.schedule(() -> {
            flight.setStatus(flight.getAvailableSeats() > 0);
            flightRepository.save(flight);
        }, 15, TimeUnit.SECONDS);
    }

    public List<ReservationResponseDTO> getReservationsByAuthenticatedIdUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        //Search the authenticated user in the DB
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isEmpty()){
            throw new AirCompanyNotFoundException("Authenticated user not found in the data base.");
        }
        User authenticatedUser = userOptional.get();

        //Search the users related reservations
        List<Reservation> userReservations = reservationRepository.findByUserId(authenticatedUser.getId());
        if(userReservations.isEmpty()){
            throw new AirCompanyNotFoundException("You do not have reservations yet. Want a ride, bro?");
        }

        return userReservations.stream()
                .map(ReservationMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<ReservationResponseDTO> getReservationByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new AirCompanyNotFoundException("The user with Id " + userId + " is not  in the data base.");
        }
        List<Reservation> userReservations = reservationRepository.findByUserId(userId);
        if(userReservations.isEmpty()){
            throw new AirCompanyNotFoundException("No reservations for this user?");
        }
        return userReservations.stream()
                .map(ReservationMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
