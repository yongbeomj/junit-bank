package shop.mtcoding.bank.temp;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

// java.util.regex.Pattern
public class RegexTest {

    @Test
    public void 한글만된다_test() {
        String value = "한글";
        boolean result = Pattern.matches("^[가-힣]+$", value);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void 한글은안된다_test() {
        String value = "abc";
        boolean result = Pattern.matches("^[^ㄱ-ㅎ가-힣]*$", value); // ^: 안에 있으면 not의 의미
        System.out.println("테스트 : " + result);
    }

    @Test
    public void 영어만된다_test() {
        String value = "ssar";
        boolean result = Pattern.matches("^[a-zA-Z]+$", value); // ^: 안에 있으면 not의 의미
        System.out.println("테스트 : " + result);
    }

    @Test
    public void 영어는안된다_test() {
        String value = "가22";
        boolean result = Pattern.matches("^[^a-zA-Z]*$", value); // ^: 안에 있으면 not의 의미
        System.out.println("테스트 : " + result);
    }

    @Test
    public void 영어와숫자만된다_test() {
        String value = "ssar2323";
        boolean result = Pattern.matches("^[a-zA-Z0-9]+$", value); // ^: 안에 있으면 not의 의미
        System.out.println("테스트 : " + result);

    }

    @Test
    public void 영어만되고_길이는최소2최대4이다_test() {
        String value = "ssar";
        boolean result = Pattern.matches("^[a-zA-Z]{2,4}$", value); // ^: 안에 있으면 not의 의미
        System.out.println("테스트 : " + result);
    }

    // username, email, fullname
    @Test
    public void user_username_test() {
        String username = "ssar";
        // 영문/숫자 2~20자 이내로 작성해주세요
        boolean result = Pattern.matches("^[a-zA-Z0-9]{2,20}$", username);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void user_fullname_test() throws Exception {
        String fullname = "쌀";
        // 영어/한글, 1자~20자
        boolean result = Pattern.matches("^[a-zA-Z가-힣]{1,20}$", fullname);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void user_email_test() throws Exception {
        String email = "ssarj@nate.com"; // ac.kr co.kr or.kr X
        // 영어,한글, 1자~20자
        boolean result = Pattern.matches("^[a-zA-Z0-9]{2,10}@[a-zA-Z0-9]{2,6}\\.[a-zA-Z]{2,3}$", email);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void account_gubun_test1() {
        String gubun = "DEPOSIT";
        boolean result = Pattern.matches("^(DEPOSIT)$", gubun);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void account_gubun_test2() {
        String gubun = "TRANSFER";
        boolean result = Pattern.matches("^(DEPOSIT|TRANSFER)$", gubun);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void account_tel_test1() {
        String tel = "010-3333-7777";
        boolean result = Pattern.matches("^[0-9]{3}-[0-9]{4}-[0-9]{4}", tel);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void account_tel_test2() {
        String tel = "01033337777";
        boolean result = Pattern.matches("^[0-9]{11}", tel);
        System.out.println("테스트 : " + result);
    }

}
