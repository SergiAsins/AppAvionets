package AppAvionets.java.AppAvionets.register;

import java.util.Map;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.HashMap;
import java.util.Set;

import AppAvionets.java.AppAvionets.roles.Role;
import AppAvionets.java.AppAvionets.roles.RoleService;
import AppAvionets.java.AppAvionets.users.User;
import AppAvionets.java.AppAvionets.users.UserRequestDTO;
import AppAvionets.java.AppAvionets.users.UserResponseDTO;
import AppAvionets.java.AppAvionets.users.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public RegisterService(UserRepository userRepository, RoleService roleService){
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

public Map<String, String> save(UserRequestDTO userRequestDTO){

    //Decode the base64 password
    String passwordDecoded = new String(Base64.getDecoder().decode(userRequestDTO.password()));
    //Encrypt the password
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String passwordEncoded = encoder.encode(userRequestDTO.password());

    //get role from the DB
    Role role = roleService.getById(userRequestDTO.role().getId());

    //Create User Entity
    User user = new User();
    user.setUsername(userRequestDTO.username());
    user.setPassword(passwordEncoded);
    user.setRoles(Set.of(role));

    //Save User entity to the repository
    userRepository.save(user);

    //Prepare response
    Map<String, String> response = new HashMap<>();
    response.put("message", "User registered successfully");
    return response;
    }
}
