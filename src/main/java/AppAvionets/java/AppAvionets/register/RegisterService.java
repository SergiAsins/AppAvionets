package AppAvionets.java.AppAvionets.register;

import java.util.Map;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.HashMap;

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

public Map<String, String> save(UserResponseDTO userData){

    Decoder decoder = Base64.getDecoder();
    byte[] decodedBytes = decoder.decode(userData.password());
    String passwordDecoded = new String(decodedBytes);

    System.out.println("<----------" + passwordDecoded);

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String passwordEncoded = encoder.encode(passwordDecoded);

    User newUser = new User(userData.name(), passwordEncoded);
    newUser.setRoles(roleService.assignDefaultRole());

    userRepository.save(newUser);

    Map<String, String> response = new HashMap<>();
    response.put("message", "Success");

    return response;
    }
}
