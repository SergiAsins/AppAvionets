package AppAvionets.java.AppAvionets.reservations;

import AppAvionets.java.AppAvionets.flights.FlightMapper;
import AppAvionets.java.AppAvionets.flights.FlightResponseDTO;
import AppAvionets.java.AppAvionets.users.UserMapper;
import AppAvionets.java.AppAvionets.users.UserResponseDTO;
import AppAvionets.java.AppAvionets.flights.Flight;
import AppAvionets.java.AppAvionets.users.User;

public class ReservationMapper {
    public static Reservation toEntity(ReservationRequestDTO reservationRequestDTO, User user, Flight flight){
        return new Reservation(
                user,
                flight,
                reservationRequestDTO.seats(),
                reservationRequestDTO.ticketTime()
        );
    }

    public static ReservationResponseDTO toResponseDto(Reservation reservation){
        UserResponseDTO userResponseDTO = UserMapper.toResponseDTO(reservation.getUser());
        FlightResponseDTO flightResponseDTO = FlightMapper.toResponseDTO(reservation.getFlight());
        return new ReservationResponseDTO(
                reservation.getIdReservation(),
                userResponseDTO,
                flightResponseDTO,
                reservation.getTicketTime(),
                reservation.getSeats()
        );
    }
}
