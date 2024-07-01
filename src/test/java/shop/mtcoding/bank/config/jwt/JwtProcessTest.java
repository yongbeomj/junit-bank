package shop.mtcoding.bank.config.jwt;

import org.junit.jupiter.api.Test;
import shop.mtcoding.bank.config.auth.LoginUser;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserEnum;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JwtProcessTest {

    private String createToken() {
        User user = User.builder()
                .id(1L)
                .role(UserEnum.ADMIN)
                .build();

        LoginUser loginUser = new LoginUser(user);
        String jwtToken = JwtProcess.create(loginUser);

        return jwtToken;
    }

    @Test
    public void create_test() throws Exception {
        // given

        // when
        String jwtToken = createToken();
        System.out.println("테스트 : " + jwtToken);

        // then
        assertTrue(jwtToken.startsWith(JwtVO.TOKEN_PREFIX));
    }

    @Test
    public void verify_test() throws Exception {
        // given
        // 테스트 시 token 값을 임의의 값을 넣어놓고 테스트 할 경우 만료시간때문에 위험
        // String jwtToken = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJiYW5rIiwiZXhwIjoxNzIwMTgyMTQyLCJpZCI6MSwicm9sZSI6IkFETUlOIn0.wuMYHPYrWuKkwCSyVSUCXh4sY-0IAdlv8fNFtf31c2i5jJxuCdwXTAe3tKdKk9FLMKI15OIZwMorn2CbdLSC3w";

        String token = createToken(); // Bearer 제거해서 처리
        String jwtToken = token.replace(JwtVO.TOKEN_PREFIX, "");

        // when
        LoginUser loginUser = JwtProcess.verify(jwtToken);
        System.out.println("테스트 : " + loginUser.getUser().getId());
        System.out.println("테스트 : " + loginUser.getUser().getRole().name());

        // then
        assertThat(loginUser.getUser().getId()).isEqualTo(1L);
        assertThat(loginUser.getUser().getRole()).isEqualTo(UserEnum.ADMIN);
    }

}