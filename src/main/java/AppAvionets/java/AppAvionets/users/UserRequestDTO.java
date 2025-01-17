package AppAvionets.java.AppAvionets.users;

import AppAvionets.java.AppAvionets.roles.Role;
import jakarta.validation.constraints.*;


public record UserRequestDTO(

        Role role,

        @NotNull(message = "The name cannot be null")
        @NotEmpty(message = "The name cannot be empty")
        String name,

        @NotNull(message = "The pwd cannot be null")
        @NotEmpty(message = "The pwd cannot be empty")
        String password
) {
}

