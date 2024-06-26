package shop.mtcoding.bank.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // select * from user where username = ?
    Optional<User> findByUsername(String username); // JPA NameQuery 작동

    // save - 이미 만들어져 있음 (JPA)
}
