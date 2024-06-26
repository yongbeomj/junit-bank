package shop.mtcoding.bank.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseDto<T> {
    // final 이유 : response dto는 한번 만들어지면 수정될 이유가 없음
    private final Integer code; // 1 성공, -1 실패
    private final String msg;
    private final T data;
}
