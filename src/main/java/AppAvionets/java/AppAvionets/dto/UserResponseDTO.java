package AppAvionets.java.AppAvionets.dto;

import AppAvionets.java.AppAvionets.entities.Role;

public record UserResponseDTO (
        Long id,
        Role role,
        String name,
        String password,
        Integer age,
        String address,
        String mail,
        String profilePicture
){
}
