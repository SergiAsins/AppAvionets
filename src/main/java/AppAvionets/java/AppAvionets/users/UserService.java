package AppAvionets.java.AppAvionets.users;

import AppAvionets.java.AppAvionets.exceptions.AirCompanyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){
        User user = UserMapper.toEntity(userRequestDTO);
        User savedUser = userRepository.save(user);
        return UserMapper.toResponseDTO(savedUser);
    }

    public List<UserResponseDTO> findAll(){
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(UserMapper::toResponseDTO).toList();
    }

    public UserResponseDTO findById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            throw new AirCompanyNotFoundException("The user with id " + id + "does not exist.");
        }
        User user = optionalUser.get();
        return UserMapper.toResponseDTO(user);
    }

    public List<UserResponseDTO> findByNameIgnoringCase(String name){
        List<User> users = userRepository.findByNameIgnoreCaseContaining(name);

        if(users.isEmpty()){
            throw new AirCompanyNotFoundException("The user with the name" + name + "does not exist.");
        }
        return users.stream()
                .map(UserMapper::toResponseDTO).toList();
    }

    public UserResponseDTO updateUserById(Long id, UserRequestDTO userRequestDTO){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();

            user.setRoles(userRequestDTO.role());
            user.setUsername(userRequestDTO.name());
            user.setPassword((userRequestDTO.password()));

            User updatedUser = userRepository.save(user);
            return UserMapper.toResponseDTO(updatedUser);
        }
        throw new AirCompanyNotFoundException("The user with the id" + id + "does not exist.");
    }

    public void deleteUserById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()){
            throw new AirCompanyNotFoundException("The user with the id" + id + "does not exist.");
        }
        userRepository.deleteById(id);
    }
}


