package AppAvionets.java.AppAvionets.profiles;

import jakarta.validation.constraints.*;

public record ProfileRequestDTO(

    @NotNull
    @NotEmpty
    String name,

    @NotBlank
    @Size(min = 9, max = 9, message = "Phone number must contain 9 numbers.")
    @Pattern(regexp = "^\\d{9}$", message = "Phone number must contain 9 numbers.")
    String phone,

    @NotNull
    @NotEmpty
    @Email(message = "The email must be in a correct electronic mal address format.")
    String email,

    @NotNull
    @NotEmpty
    String address,

    String picture,

    @NotNull(message = "The profile ID cannot be null")
    Long userId

    ){

}

