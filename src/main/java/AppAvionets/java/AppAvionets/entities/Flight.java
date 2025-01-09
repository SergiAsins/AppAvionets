package AppAvionets.java.AppAvionets.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFlight;

    private String flightNumber;
    private Boolean status;

    //?¿ origin
    @JoinColumn(name = "origin")
    private Airport origin;

    @ManyToMany
    @JoinColumn(name = "destination")
    private Airport destination;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    //getters and setters
    public Long getIdFlight() {
        return idFlight;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
}
