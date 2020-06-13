package lozm.object.dto;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class ApiException extends RuntimeException {

    private ApiResponseCode status;
    private String message;

    public ApiException(ApiResponseCode status, Exception e) {
        super(e);
        this.status = status;
        this.message = status.getMessage();
    }

    public ApiException(ApiResponseCode status, String message, Exception e) {
        super(e);
        this.status = status;
        this.message = message;
    }

    public ApiException(ApiResponseCode status) {
        this.status = status;
        this.message = status.getMessage();
    }

    public ApiException(ApiResponseCode status, String message) {
        this.status = status;
        if (StringUtils.isBlank(message)) {
            this.message = status.getMessage();
        } else {
            this.message = message;
        }
    }

    public ApiException(ApiResponseCode status, String message, String code) {
        this.status = status;
        this.message = message;
    }
}
