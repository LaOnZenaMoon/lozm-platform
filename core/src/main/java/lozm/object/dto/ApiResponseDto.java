package lozm.object.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class ApiResponseDto<T> {
    public static final ApiResponseDto<String> DEFAULT_OK = new ApiResponseDto<>(ApiResponseCode.OK);
    public static final ApiResponseDto<String> DEFAULT_UNAUTHORIZED = new ApiResponseDto<>(ApiResponseCode.UNAUTHORIZED);

    private boolean success;
    private String code;
    private String message;
    private T data;

    private ApiResponseDto(ApiResponseCode status) {
        this.bindStatus(status);
    }

    private ApiResponseDto(ApiResponseCode status, T data) {
        this.bindStatus(status);
        this.data = data;
    }

    private ApiResponseDto(Boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    private ApiResponseDto(ApiResponseCode code, String message, T data) {
        this.success = code.isSuccess();
        this.code = code.name();
        this.message = message;
        this.data = data;
    }

    private ApiResponseDto(ApiResponseCode code, ApiException e) {
        this.success = code.isSuccess();
        this.code = code.name();
        this.message = e.getMessage();
    }

    private void bindStatus(ApiResponseCode status) {
        this.success = status.isSuccess();
        this.code = status.name();
        this.message = status.getMessage();
    }

    public static <T> ApiResponseDto<T> createOK(T data) {
        return new ApiResponseDto<>(ApiResponseCode.OK, data);
    }

    public static ApiResponseDto<String> createException(ApiException e) {
        return new ApiResponseDto<>(e.getStatus(), e);
    }

    public static ApiResponseDto<String> createException(ApiResponseCode code, String message) {
        return new ApiResponseDto<>(code, message, "");
    }

    public static <T> ApiResponseDto<T> createException(ApiResponseCode code, T data) {
        return new ApiResponseDto<>(code, data);
    }

    public static <T> ApiResponseDto<T> createException(ApiResponseCode code, String message, T data) {
        return new ApiResponseDto<>(code, message, data);
    }
}
