package AppAvionets.java.AppAvionets.users;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        User user = new User("Jesus", "Jesus33");
        testEntityManager.persist(user);
    }

    //bug3
    //unitary test of UserRepository - method findById()
    @Test
    public void testFindUserByIdFound(){
        Optional<User> user = userRepository.findById(1L);
        assertEquals(user.get().getId(), 1L);
    }
}