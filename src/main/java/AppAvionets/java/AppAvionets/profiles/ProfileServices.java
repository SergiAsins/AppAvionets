package AppAvionets.java.AppAvionets.profiles;

import AppAvionets.java.AppAvionets.exceptions.users.AirCompanyUserNotFoundException;
import AppAvionets.java.AppAvionets.users.User;
import AppAvionets.java.AppAvionets.users.UserRepository;
import org.springframework.stereotype.Service;
import AppAvionets.java.AppAvionets.exceptions.AirCompanyNotFoundException;
import AppAvionets.java.AppAvionets.exceptions.general.AirCompanyAlreadyExistsException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServices {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileServices(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public Object createProfile(ProfileRequestDTO profileRequestDTO) throws AirCompanyAlreadyExistsException, AirCompanyUserNotFoundException {
        Optional<Profile> existsProfile = profileRepository.findByEmail(profileRequestDTO.email());
        if (existsProfile.isPresent())
            throw new AirCompanyAlreadyExistsException("Profile already exists with this email.");

        Optional<User> userOptional = userRepository.findById(profileRequestDTO.userId());
        if (userOptional.isEmpty()) {
            throw new AirCompanyUserNotFoundException("User not found with id " + profileRequestDTO.userId());
        }

        Profile profile = ProfileMapper.toEntity(profileRequestDTO, userOptional.get());
        Profile savedProfile = profileRepository.save(profile);
        return ProfileMapper.toResponse(savedProfile);
    }

    public ProfileResponseDTO findById(Long id) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);

        if (optionalProfile.isEmpty()) {
            throw new AirCompanyNotFoundException("The profile with id " + id + " does not exist.");
        }

        Profile profile = optionalProfile.get();
        return ProfileMapper.toResponse(profile);
    }

    public List<ProfileResponseDTO> findall() {
        List<Profile> profileList = profileRepository.findAll();
        return profileList.stream()
                .map(ProfileMapper::toResponse)
                .toList();
    }

    public List<ProfileResponseDTO> findByEmailIgnoreCaseContaining(String email) {
        List<Profile> profileList = profileRepository.findByEmailIgnoreCaseContaining(email);

        if (profileList.isEmpty()) {
            throw new AirCompanyNotFoundException("The profile with email " + email + " does not exist.");
        }
        return profileList.stream()
                .map(ProfileMapper::toResponse)
                .toList();
    }

}

