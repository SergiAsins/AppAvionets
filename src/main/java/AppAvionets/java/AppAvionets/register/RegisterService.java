package AppAvionets.java.AppAvionets.register;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.HashMap;
import java.util.Map;
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

    System.out.println("-------------" + userRequestDTO.password());
    //Decode the base64 password
    Decoder decoder = Base64.getDecoder();
    byte[] decodedBytes = decoder.decode(userRequestDTO.password());
    String passwordDecoded = new String(decodedBytes);

    System.out.println("<------------ " + passwordDecoded);

    //encrypt the password
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncoded = encoder.encode(passwordDecoded);

    //retrieve the role from the DB
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
