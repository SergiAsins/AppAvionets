package AppAvionets.java.AppAvionets.register;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import AppAvionets.java.AppAvionets.users.UserRequestDTO;
import AppAvionets.java.AppAvionets.users.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "${api-endpoint}/register")
public class RegisterController {

    private final RegisterService service;

    public RegisterController(RegisterService service) {
        this.service = service;
    }

    @PostMapping("")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserRequestDTO newUser){
        Map<String, String> response = service.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

