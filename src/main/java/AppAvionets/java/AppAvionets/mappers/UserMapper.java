package AppAvionets.java.AppAvionets.mappers;

import AppAvionets.java.AppAvionets.dto.UserRequestDTO;
import AppAvionets.java.AppAvionets.dto.UserResponseDTO;
import AppAvionets.java.AppAvionets.entities.User;

public class UserMapper {
    public static User toEntity(UserRequestDTO userRequestDTO){
        return new User(
                userRequestDTO.role(),
                userRequestDTO.name(),
                userRequestDTO.password(),
                userRequestDTO.age(),
                userRequestDTO.email(),
                userRequestDTO.address(),
                userRequestDTO.profilePicture());
    }

    public static UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getRole(),
                user.getName(),
                user.getPassword(),
                user.getAge(),
                user.getAddress(),
                user.getMail(),
                user.getProfilePicture());
    }
}
