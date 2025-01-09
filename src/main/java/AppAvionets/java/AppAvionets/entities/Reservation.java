package AppAvionets.java.AppAvionets.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;

    private LocalDateTime ticketDateTime;
    private int seats;

    @ManyToOne
    @JoinColumn(name ="flightId")
    private Flight flight;
}
