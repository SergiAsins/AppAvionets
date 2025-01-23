package AppAvionets.java.AppAvionets.profiles;

import AppAvionets.java.AppAvionets.users.User;

public record ProfileResponseDTO (
    Long id,
    String name,
    String phone,
    String email,
    String picture,
    User user
    ){
}
