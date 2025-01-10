package AppAvionets.java.AppAvionets.dto;

import AppAvionets.java.AppAvionets.entities.Role;
import jakarta.validation.constraints.*;


public record UserRequestDTO(

        Role role,

        @NotNull(message = "The name cannot be null")
        @NotEmpty(message = "The name cannot be empty")
        String name,

        @NotNull(message = "The pwd cannot be null")
        @NotEmpty(message = "The pwd cannot be empty")
        String password,

        Integer age,

        @NotNull(message = "The address cannot be null")
        @NotEmpty(message = "The address cannot be empty")
        String address,

        @NotNull(message = "The email cannot be null")
        @NotEmpty(message = "The email cannot be empty")
        @Email(message = "The email must be a correctly formatted address")
        String email,

        String profilePicture
) {
}

