package AppAvionets.java.AppAvionets.airports;

import jakarta.validation.constraints.*;

public record AirportRequestDTO(

    @NotNull(message = "The username cannot be null")
    @NotEmpty(message = "The username cannot be empty")
    @Pattern(regexp = "^[A-Z]{3}-.*$", message = "The name must follow the pattern: three uppercase letters, a dash, and any text.")
    String name,

    @NotNull(message = "The city cannot be null")
    @NotEmpty(message = "The city cannot be empty")
    String city,

    @NotNull(message = "The country cannot be null")
    @NotEmpty(message = "The country cannot be empty")
    String country
){
}

