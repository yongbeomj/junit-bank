package shop.mtcoding.bank.domain.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // jpa query method
    // select * from account where number = :number
    // 신경 안써도 됨 : checkpoint : 리팩토링 필요 (계좌 소유자 확인 시 쿼리가 2번 나가기 때문에 join fetch) - accopunt.getUser().getId()
    // join fetch를 하면 조인해서 객체에 값을 미리 가져올 수 있다.
    // @Query("SELECT ac FROM Account ac JOIN FETCH ac.user u WHERE ac.number = :number")
    Optional<Account> findByNumber(@Param("number") Long number);

    // jpa query method
    // select * from account where user_id = :id
    List<Account> findByUser_Id(Long id);

}
