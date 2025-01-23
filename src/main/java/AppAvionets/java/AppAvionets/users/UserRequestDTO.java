package AppAvionets.java.AppAvionets.users;

import AppAvionets.java.AppAvionets.roles.Role;
import jakarta.validation.constraints.*;


public record UserRequestDTO(

        Role role,

        @NotNull(message = "The username cannot be null")
        @NotEmpty(message = "The username cannot be empty")
        String username,

        @NotNull(message = "The pwd cannot be null")
        @NotEmpty(message = "The pwd cannot be empty")
        String password
) {
}

