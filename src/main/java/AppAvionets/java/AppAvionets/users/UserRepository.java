package AppAvionets.java.AppAvionets.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //JpaRepository already has CRUD
    Optional<User> findById(Long Id);

    List<User> findByUsernameIgnoreCaseContaining(String username);

    public Optional<User> findByUsername(String username);

}