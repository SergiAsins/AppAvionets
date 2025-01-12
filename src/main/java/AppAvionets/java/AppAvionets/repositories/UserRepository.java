package AppAvionets.java.AppAvionets.repositories;

import AppAvionets.java.AppAvionets.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //JpaRepository already has CRUD
    Optional<User> findById(Long Id);

    List<User> findByNameIgnoreCaseContaining(String name);

}