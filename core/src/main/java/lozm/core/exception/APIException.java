package lozm.core.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIException extends RuntimeException {

    private String code;
    private String message;

    public APIException(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
