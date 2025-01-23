package AppAvionets.java.AppAvionets.profiles;

import AppAvionets.java.AppAvionets.users.User;
import jakarta.validation.Valid;

public class ProfileMapper {
    public static Profile toEntity(@Valid ProfileRequestDTO profileRequestDTO, User user){
        return new Profile(
                profileRequestDTO.name(),
                profileRequestDTO.phone(),
                profileRequestDTO.address(),
                profileRequestDTO.email(),
                profileRequestDTO.picture(),
                user
        );
    }

    public static ProfileResponseDTO toResponse(Profile profile){
        return new ProfileResponseDTO(
                profile.getId(),
                profile.getPicture(),
                profile.getName(),
                profile.getPhone(),
                profile.getEmail(),
                profile.getUser()
        );
    }
}
