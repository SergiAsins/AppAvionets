package AppAvionets.java.AppAvionets.users;

import AppAvionets.java.AppAvionets.roles.Role;

import java.util.Set;

public record UserResponseDTO (
        Long id,
        Set<Role> roles,
        String name,
        String password
){
}
