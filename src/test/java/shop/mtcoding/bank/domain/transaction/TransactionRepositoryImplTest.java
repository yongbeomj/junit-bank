package shop.mtcoding.bank.domain.transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import shop.mtcoding.bank.config.dummy.DummyObject;
import shop.mtcoding.bank.domain.account.Account;
import shop.mtcoding.bank.domain.account.AccountRepository;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;

@ActiveProfiles("test")
@DataJpaTest // DB 관련된 Bean이 다 올라온다
public class TransactionRepositoryImplTest extends DummyObject {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        autoincrementReset(); // auto increment 초기화 (@DataJpaTest에서는 truncate 사용하지 말 것)
        dataSetting();
        em.clear();
    }

    @Test
    public void findTransactionList_all_test() throws Exception {
        // given
        Long accountId = 1L;

        // when
        List<Transaction> transactionListPS = transactionRepository.findTransactionList(accountId, "ALL", 0);
        transactionListPS.forEach((t) -> {
            System.out.println("테스트 : id " + t.getId());
            System.out.println("테스트 : amount " + t.getAmount());
            System.out.println("테스트 : Sender " + t.getSender());
            System.out.println("테스트 : Receiver " + t.getReceiver());
            System.out.println("테스트 : WithdrawAccount잔액 " + t.getWithdrawAccountBalance());
            System.out.println("테스트 : DepositAccount잔액 " + t.getDepositAccountBalance());
            System.out.println("테스트 : 잔액 : " + t.getWithdrawAccount().getBalance());
//            System.out.println("테스트 : fullname " + t.getWithdrawAccount().getUser().getFullname());
            System.out.println("테스트 : =================================");
        });

        // then

    }

    @Test
    public void dataJpa_test1() throws Exception {
        List<Transaction> transactionList = transactionRepository.findAll();
        transactionList.forEach((transaction) -> {
            System.out.println("테스트 : " + transaction.getId());
            System.out.println("테스트 : " + transaction.getSender());
            System.out.println("테스트 : " + transaction.getReceiver());
            System.out.println("테스트 : " + transaction.getGubun());
            System.out.println("테스트 : ========================");
        });
    }

    @Test
    public void dataJpa_test2() throws Exception {
        List<Transaction> transactionList = transactionRepository.findAll();
        transactionList.forEach((transaction) -> {
            System.out.println("테스트 : " + transaction.getId());
            System.out.println("테스트 : " + transaction.getSender());
            System.out.println("테스트 : " + transaction.getReceiver());
            System.out.println("테스트 : " + transaction.getGubun());
            System.out.println("테스트 : ========================");
        });
    }

    private void dataSetting() {
        User ssar = userRepository.save(newUser("ssar", "쌀"));
        User cos = userRepository.save(newUser("cos", "코스,"));
        User love = userRepository.save(newUser("love", "러브"));
        User admin = userRepository.save(newUser("admin", "관리자"));

        Account ssarAccount1 = accountRepository.save(newAccount(1111L, ssar));
        Account cosAccount = accountRepository.save(newAccount(2222L, cos));
        Account loveAccount = accountRepository.save(newAccount(3333L, love));
        Account ssarAccount2 = accountRepository.save(newAccount(4444L, ssar));

        Transaction withdrawTransaction1 = transactionRepository
                .save(newWithdrawTransaction(ssarAccount1, accountRepository));
        Transaction depositTransaction1 = transactionRepository
                .save(newDepositTransaction(cosAccount, accountRepository));
        Transaction transferTransaction1 = transactionRepository
                .save(newTransferTransaction(ssarAccount1, cosAccount, accountRepository));
        Transaction transferTransaction2 = transactionRepository
                .save(newTransferTransaction(ssarAccount1, loveAccount, accountRepository));
        Transaction transferTransaction3 = transactionRepository
                .save(newTransferTransaction(cosAccount, ssarAccount1, accountRepository));
    }

    private void autoincrementReset() {
        em.createNativeQuery("ALTER TABLE user_tb ALTER COLUMN id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE account_tb ALTER COLUMN id RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE transaction_tb ALTER COLUMN id RESTART WITH 1").executeUpdate();
    }

}