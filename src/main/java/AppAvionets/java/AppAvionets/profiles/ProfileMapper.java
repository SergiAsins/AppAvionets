package AppAvionets.java.AppAvionets.profiles;

import AppAvionets.java.AppAvionets.users.User;
import AppAvionets.java.AppAvionets.users.UserMapper;
import jakarta.validation.Valid;

public class ProfileMapper {
    public static Profile toEntity(@Valid ProfileRequestDTO profileRequestDTO, User user){
        return new Profile(
                profileRequestDTO.name(),
                profileRequestDTO.phone(),
                profileRequestDTO.email(),
                profileRequestDTO.address(),
                profileRequestDTO.picture() == null || profileRequestDTO.picture().isEmpty()
                        ? "www.aircompany.images/default.ma"
                        : profileRequestDTO.picture(),
                user
        );
    }

    public static ProfileResponseDTO toResponse(Profile profile){
        return new ProfileResponseDTO(
                profile.getId(),
                profile.getName(),
                profile.getPhone(),
                profile.getEmail(),
                profile.getPicture(),
                UserMapper.toResponseDTO(profile.getUser())
        );
    }
}
