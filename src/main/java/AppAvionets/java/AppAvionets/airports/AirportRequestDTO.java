package AppAvionets.java.AppAvionets.airports;

import jakarta.validation.constraints.*;

public record AirportRequestDTO(

    @NotNull(message = "The codeAirport cannot be null")
    @NotEmpty(message = "The codeAirport cannot be empty")
    @Pattern(regexp = "^[A-Z]{3}$", message = "The codeAirport must be three uppercase letters.")
    String codeAirport,

    @NotNull(message = "The username cannot be null")
    @NotEmpty(message = "The username cannot be empty")
    String name,

    @NotNull(message = "The city cannot be null")
    @NotEmpty(message = "The city cannot be empty")
    String city,

    @NotNull(message = "The country cannot be null")
    @NotEmpty(message = "The country cannot be empty")
    String country
){
}

