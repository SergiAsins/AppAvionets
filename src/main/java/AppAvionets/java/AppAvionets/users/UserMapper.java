package AppAvionets.java.AppAvionets.users;

import java.util.Set;

public class UserMapper {
    public static User toEntity(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setUsername(userRequestDTO.username());
        user.setPassword(userRequestDTO.password());
        user.setRoles(Set.of(userRequestDTO.role())); //converts the Role unique into a Set
        return user;
    }

    public static UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getRoles(),
                user.getUsername());
    }
}
