package AppAvionets.java.AppAvionets.entities;

import jakarta.persistence.*;
//import lombok.*;
import java.sql.Timestamp;
import java.util.Optional;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idFlight;

    String flightNumber;
    Boolean status;

    @OneToOne
    @JoinColumn(name = "origin_id")
    Airport origin;

    @OneToOne
    @JoinColumn(name = "destination_id")
    Airport destination;

    Timestamp departureTime;
    Timestamp arrivalTime;
    Integer availableSeats;

    public Flight() {
    }

    public Flight(String flightNumber, Boolean status, Airport origin, Airport destination, Timestamp departureTime, Timestamp arrivalTime, Integer availableSeats) {
        this.flightNumber = flightNumber;
        this.status = status;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
    }

    public Flight (Optional<Flight> byId){
    }

    //getters and setters}
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

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

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }
}
