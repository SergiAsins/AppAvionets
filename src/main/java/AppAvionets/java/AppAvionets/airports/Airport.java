package AppAvionets.java.AppAvionets.airports;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
//import.lombok.*;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Z]{3}", message = "The airportCode must be three uppercase letters")
    private String codeAirport;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "The city must start with an uppercase letter and can contain only letters and spaces.")
    private String name;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "The city must start with an uppercase letter and can contain only letters and spaces.")
    private String city;

    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]*$", message = "The country must start with an uppercase letter and can contain only letters and spaces.")
    private String country;

    public Airport() {
    }

    public Airport(String codeAirport, String name, String city, String country) {
        this.codeAirport = codeAirport;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public void setCodeAirport(String codeAirport){this.codeAirport = codeAirport;}
    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public String getCodeAirport(){return codeAirport;}

}
