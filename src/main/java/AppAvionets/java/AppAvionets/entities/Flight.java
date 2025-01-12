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

    String flightHumber;
    Boolean status;

    @OneToOne
    @JoinColumn(name = "origin_id")
    Airport origin;

    @OneToOne
    @JoinColumn(name = "destination_id")
    Airport destination;

    Timestamp departure;
    Timestamp arrival;
    Integer availableSeats;

    public Flight() {
    }

    public Flight(Timestamp arrival, Timestamp departure, Airport destination, Airport origin, String flightHumber) {
        this.arrival = arrival;
        this.departure = departure;
        this.destination = destination;
        this.origin = origin;
        this.flightHumber = flightHumber;
    }

    public Flight (Optional<Flight> byId){
    }

    //getters and setters
    public void setFlightHumber(String flightHumber) {
        this.flightHumber = flightHumber;
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

    public void setDeparture(Timestamp departure) {
        this.departure = departure;
    }

    public void setArrival(Timestamp arrival) {
        this.arrival = arrival;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Long getIdFlight() {
        return idFlight;
    }

    public String getFlightHumber() {
        return flightHumber;
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

    public Timestamp getDeparture() {
        return departure;
    }

    public Timestamp getArrival() {
        return arrival;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }
}
