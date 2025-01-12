package AppAvionets.java.AppAvionets.mappers;

import AppAvionets.java.AppAvionets.dto.ReservationRequestDTO;
import AppAvionets.java.AppAvionets.dto.ReservationResponseDTO;
import AppAvionets.java.AppAvionets.dto.FlightResponseDTO;
import AppAvionets.java.AppAvionets.dto.UserResponseDTO;
import AppAvionets.java.AppAvionets.entities.Reservation;
import AppAvionets.java.AppAvionets.entities.Flight;
import AppAvionets.java.AppAvionets.entities.User;

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
