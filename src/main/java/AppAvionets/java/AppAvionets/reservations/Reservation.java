package AppAvionets.java.AppAvionets.reservations;

import AppAvionets.java.AppAvionets.flights.Flight;
import AppAvionets.java.AppAvionets.users.User;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idReservation;

    @Column(nullable = false, updatable = false)
    private Timestamp ticketTime;

    @ManyToOne
    @JoinColumn(name = "id_flight", nullable = false)
    private Flight flight;

    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;

    private Integer seats;

    public Reservation() {
    }

    public Reservation(User user, Flight flight, Integer seats) {
        this.user = user;
        this.flight = flight;
        this.seats = seats;
        this.ticketTime = Timestamp.from(Instant.now());
    }

    @PrePersist
    protected void onCreate(){
        this.ticketTime = Timestamp.from(Instant.now());
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
