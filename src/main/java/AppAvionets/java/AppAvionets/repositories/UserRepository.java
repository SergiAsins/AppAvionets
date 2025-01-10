package AppAvionets.java.AppAvionets.repositories;

import AppAvionets.java.AppAvionets.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    //JpaRepository already has CRUD
    List<User> findByNameIgnoreCaseContaining(String name);

}