package lozm.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponseDto<T> {

    private boolean success;
    private String message;
    private T data;

    public APIResponseDto() {
        this.success = false;
    }

    public APIResponseDto(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

}
