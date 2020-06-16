package lozm.global.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("리소스를 찾지 못했습니다.");
    }

    public NotFoundException(String message) {
        super(message);
    }

}