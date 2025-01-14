package AppAvionets.java.AppAvionets.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

import java.sql.Timestamp;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idReservation;

    //current time when posts the reservation
    private Timestamp ticketTime;

    @OneToOne
    @JoinColumn(name = "IdFlight")
    Flight flight;

    @OneToOne
    @JoinColumn(name = "idUser")
    User user;

    Integer seats;

    public Reservation() {
    }

    public Reservation(User user, Flight flight, Integer seats, Timestamp ticketTime) {
        this.user = user;
        this.flight = flight;
        this.seats = seats;
        this.ticketTime = ticketTime;
    }

    //getters and setters}
    public Timestamp getTicketTime() {
        return ticketTime;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public Flight getFlight() {
        return flight;
    }

    public User getUser() {
        return user;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public void setTicketTime(Timestamp ticketTime) {
        this.ticketTime = ticketTime;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }
}
