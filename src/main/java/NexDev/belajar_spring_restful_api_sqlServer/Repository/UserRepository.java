package NexDev.belajar_spring_restful_api_sqlServer.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import NexDev.belajar_spring_restful_api_sqlServer.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // void save(User users);
    Optional<User> findFirstByToken(String token);

    Optional<User> findUserByToken(String token);

    Optional<User> findUserByUsername(String username);
}
