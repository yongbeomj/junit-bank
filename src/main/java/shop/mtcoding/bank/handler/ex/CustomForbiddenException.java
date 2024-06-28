package shop.mtcoding.bank.handler.ex;

// 추후 사용
public class CustomForbiddenException extends RuntimeException {
    public CustomForbiddenException(String message) {
        super(message);
    }
}
