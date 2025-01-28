package AppAvionets.java.AppAvionets.profiles;

public record ProfileResponseDTO (
        Long id,
        String name,
        String phone,
        String email,
        String picture,
        AppAvionets.java.AppAvionets.users.UserResponseDTO user
    ){
}
