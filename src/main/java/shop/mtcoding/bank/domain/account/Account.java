package shop.mtcoding.bank.domain.account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.handler.ex.CustomApiException;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor // 스프링이 User 객체 생성할 때 빈생성자로 new 하기 때문! (고정)
@Getter
@EntityListeners(AuditingEntityListener.class) // CreatedDate, LastModifiedDate 작동하게 해줌
@Table(name = "account_tb")
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 4) // 길이 수정
    private Long number; // 계좌번호

    @Column(nullable = false, length = 4)
    private Long password; // 계좌 비밀번호

    @Column(nullable = false)
    private Long balance; // 잔액 (default 1000원)

    // 항상 ORM에서 FK의 주인은 Many entity 쪽이다
    @ManyToOne(fetch = FetchType.LAZY) // account.getUser().아무필드호출() == Lazy 발동
    private User user;

    @CreatedDate // Insert
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // Insert or Update
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Account(Long id, Long number, Long password, Long balance, User user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.number = number;
        this.password = password;
        this.balance = balance;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void checkOwner(Long userId) {
        if (user.getId() != userId) { // Laze 로딩이어도 id를 조회할 때는 select 쿼리가 날라가지 않는다
            throw new CustomApiException("계좌 소유자가 아닙니다");
        }
    }

    public void deposit(Long amount) {
        balance += amount;
    }

    public void checkSamePassword(Long password) {
        if (this.password.longValue() != password) {
            throw new CustomApiException("계좌 비밀번호 검증에 실패했습니다");
        }
    }

    public void checkBalance(Long amount) {
        if (this.balance < amount) {
            throw new CustomApiException("계좌 잔액이 부족합니다");
        }
    }

    public void withdraw(Long amount) {
        checkBalance(amount);
        balance -= amount;
    }
}
