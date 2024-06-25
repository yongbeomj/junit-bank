package shop.mtcoding.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // CreatedDate, LastModifiedDate 작동하게 해줌
@SpringBootApplication
public class BankApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }

}
