package shop.mtcoding.bank.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // jpa query method
    // select * from account where number = :number
    // checkpoint : 리팩토링 필요 (계좌 소유자 확인 시 쿼리가 2번 나가기 때문에 join fetch)
    Optional<Account> findByNumber(Long number);
    
}
