package shop.mtcoding.bank.config.dummy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserRepository;

@Configuration
public class DummyDevInit extends DummyObject{

    @Profile("dev") // prod 모드에서 실행되면 안되므로 실행 서버 제한
    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return (args -> {
            // 서버 실행 시 무조건 실행
            User ssar = userRepository.save(newUser("ssar", "쌀"));
        } );
    }
}
