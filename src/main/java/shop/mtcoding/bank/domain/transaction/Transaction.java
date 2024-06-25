package shop.mtcoding.bank.domain.transaction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import shop.mtcoding.bank.domain.account.Account;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor // 스프링이 User 객체 생성할 때 빈생성자로 new 하기 때문! (고정)
@Getter
@EntityListeners(AuditingEntityListener.class) // CreatedDate, LastModifiedDate 작동하게 해줌
@Table(name = "transaction_tb")
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account withdrawAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account depositAccount;

    @Column(nullable = false)
    private Long amount;

    private Long withdrawAccountBalance; // transaction 출금 히스토리 (1111 계좌 -> 1000원 -> 500원 -> 200원)
    private Long depositAccountBalance; // transaction 입금 히스토리

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionEnum gubun; // WITHDRAW, DEPOSIT, TRANSFER, ALL

    // 계좌가 사라져도 로그는 남아야 함
    private String sender;
    private String receiver;
    private String tel;

    @CreatedDate // Insert
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // Insert or Update
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Transaction(Long id, Account withdrawAccount, Account depositAccount, Long amount, Long withdrawAccountBalance, Long depositAccountBalance, TransactionEnum gubun, String sender, String receiver, String tel, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.withdrawAccount = withdrawAccount;
        this.depositAccount = depositAccount;
        this.amount = amount;
        this.withdrawAccountBalance = withdrawAccountBalance;
        this.depositAccountBalance = depositAccountBalance;
        this.gubun = gubun;
        this.sender = sender;
        this.receiver = receiver;
        this.tel = tel;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
