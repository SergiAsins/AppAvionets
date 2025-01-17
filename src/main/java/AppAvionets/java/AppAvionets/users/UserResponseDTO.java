package AppAvionets.java.AppAvionets.users;

import AppAvionets.java.AppAvionets.roles.Role;

public record UserResponseDTO (
        Long id,
        Role role,
        String name,
        String password
){
}
