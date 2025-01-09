package AppAvionets.java.AppAvionets.repositories;

import AppAvionets.java.AppAvionets.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  // we don't need additional methods now, because JpaRepository already has CRUD
}