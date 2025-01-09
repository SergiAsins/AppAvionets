package AppAvionets.java.AppAvionets.entities;

import jakarta.persistence.*;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAirport;

    private String name;
    private String city;
    private String country;
    private int seatsAvailable;

    //getters and setters
    public Long getIdAirport() {
        return idAirport;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setIdAirport(Long idAirport) {
        this.idAirport = idAirport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }
}
