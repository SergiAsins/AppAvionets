package AppAvionets.java.AppAvionets.dto;

import jakarta.validation.constraints.*;

public record AirportRequestDTO(

    @NotNull(message = "The name cannot be null")
    @NotEmpty(message = "The name cannot be empty")
    String name,

    @NotNull(message = "The city cannot be null")
    @NotEmpty(message = "The city cannot be empty")
    String city,

    @NotNull(message = "The country cannot be null")
    @NotEmpty(message = "The country cannot be empty")
    String country
){
}

