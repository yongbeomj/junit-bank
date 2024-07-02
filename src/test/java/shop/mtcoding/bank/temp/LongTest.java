package shop.mtcoding.bank.temp;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongTest {
    @Test
    public void long_test() throws Exception {
        // given
        Long number1 = 1111L;
        Long number2 = 1111L;

        // when
        if (number1.longValue() == number2.longValue()) {
            System.out.println("테스트 : 동일합니다");
        } else {
            System.out.println("테스트 : 동일하지 않습니다");
        }

        Long amount1 = 100L;
        Long amount2 = 1000L;

        if (amount1 < amount2) {
            System.out.println("테스트 : amount1이 작습니다");
        } else {
            System.out.println("테스트 : amount1이 큽니다");
        }

        // then

    }

    @Test
    public void long_test2() {
        // given (2의 8승 - 256 범위 (-126 ~ +127))
        Long v1 = 1000L;
        Long v2 = 1000L;

        // when
        if (v1 == v2) {
            System.out.println("테스트 : v1이 작음");
        } else {
            System.out.println("테스트 : v2이 작음");
        }

        // then

    }

    @Test
    public void long_test3() {
        // given (2의 8승 - 256 범위 (-126 ~ +127))
        Long v1 = 12000008L;
        Long v2 = 12000008L;

        // when
        Assertions.assertThat(v1).isEqualTo(v2);

        // then

    }
}
