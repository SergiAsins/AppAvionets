package AppAvionets.java.AppAvionets.register;

import java.util.*;
import java.util.Base64.Decoder;

import AppAvionets.java.AppAvionets.exceptions.general.AirCompanyAlreadyExistsException;
import AppAvionets.java.AppAvionets.roles.RoleService;
import AppAvionets.java.AppAvionets.users.User;
import AppAvionets.java.AppAvionets.users.UserRequestDTO;
import AppAvionets.java.AppAvionets.users.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    public RegisterService(UserRepository userRepository, RoleService roleService){
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

public Map<String, String> save(UserRequestDTO userRequestDTO){

    Optional<User> existingUser = userRepository.findByUsername(userRequestDTO.username());
    if(existingUser.isPresent()){
        throw new AirCompanyAlreadyExistsException("The user with this name already exist.");
    }

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
    //Role role = roleService.getById(userRequestDTO.role().getId());

    //Create User Entity
    User newUser = new User(userRequestDTO.username(), passwordEncoded);

    //manage RoleNotFoundException
    try {
        newUser.setRoles(roleService.assignDefaultRole(newUser.getId()));
    } catch (RoleNotFoundException e){
        throw new RuntimeException("Default role could not be assigned. Please contact support.", e);
    };

    //Save User entity to the repository
    userRepository.save(newUser);

    //Prepare response
    Map<String, String> response = new HashMap<>();
    response.put("message", "User registered successfully");
    return response;
    }
}
