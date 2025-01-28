package AppAvionets.java.AppAvionets.profiles;

import jakarta.validation.Valid;
import AppAvionets.java.AppAvionets.exceptions.general.AirCompanyAlreadyExistsException;
import AppAvionets.java.AppAvionets.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileServices profileServices;
    private final ProfileRepository profileRepository;

    public ProfileController(ProfileServices profileServices, ProfileRepository profileRepository) {
        this.profileServices = profileServices;
        this.profileRepository = profileRepository;
    }

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> createProfile(@RequestBody @Valid ProfileRequestDTO profileRequestDTO){
        ProfileResponseDTO profileResponseDTO = (ProfileResponseDTO) profileServices.createProfile(profileRequestDTO);
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<ProfileResponseDTO>> getAllProfiles(){
        List<ProfileResponseDTO> allProfiles = profileServices.findall();
        return new ResponseEntity<>(allProfiles, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public List<ProfileResponseDTO> getProfileByIdOrEmail(@PathVariable String email){
        try{
            Long profileId = Long.parseLong((email));
            return Collections.singletonList(profileServices.findById(profileId));
        } catch (NumberFormatException exception){
            return profileServices.findByEmailIgnoreCaseContaining(email);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> updateProfile(@PathVariable Long id, @RequestBody @Valid ProfileRequestDTO profileRequestDTO){
        ProfileResponseDTO profileResponseDTO = profileServices.findById(id);
        return new ResponseEntity<>(profileResponseDTO, HttpStatus.OK);
    }
}
