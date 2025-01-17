package AppAvionets.java.AppAvionets.users;

public class UserMapper {
    public static User toEntity(UserRequestDTO userRequestDTO){
        return new User(
                userRequestDTO.role(),
                userRequestDTO.name(),
                userRequestDTO.password());
    }

    public static UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getRoles(),
                user.getUsername(),
                user.getPassword());
    }
}
