package shop.mtcoding.bank.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.mtcoding.bank.config.dummy.DummyObject;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static shop.mtcoding.bank.dto.user.UserReqDto.JoinReqDto;
import static shop.mtcoding.bank.dto.user.UserRespDto.JoinRespDto;

// Spring 관련 Bean들이 하나도 없는 환경!!
@ExtendWith(MockitoExtension.class)
public class UserServiceTest extends DummyObject {

    @InjectMocks
    private UserService userService;

    @Mock // 실제로 띄울 필요X, 가짜로 메모리에 띄워서 위의 가짜 userService에 inject해준다.
    private UserRepository userRepository;

    @Spy // 진짜 passwordEncoder를 가져옴
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void 회원가입_test() throws Exception {
        // given
        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setUsername("ssar");
        joinReqDto.setPassword("1234");
        joinReqDto.setEmail("ssar@nate.com");
        joinReqDto.setFullname("쌀");

        // stub 1 - 매개변수에 뭐라도 들어가면 Optional의 empty를 리턴
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());
        // when(userRepository.findByUsername(any())).thenReturn(Optional.of(new User()));

        // stub 2 - 저장되면 유저 정보를 리턴
        User ssar = newMockUser(1L, "ssar", "쌀");
        when(userRepository.save(any())).thenReturn(ssar);

        // when
        JoinRespDto joinRespDto = userService.회원가입(joinReqDto);
        System.out.println("테스트 : " + joinRespDto);

        // then
        assertThat(joinRespDto.getId()).isEqualTo(1L);
        assertThat(joinRespDto.getUsername()).isEqualTo("ssar");
    }
}