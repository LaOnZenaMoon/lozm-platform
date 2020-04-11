package lozm.core.dto.sign;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

public class PostSignDto {

    @Getter
    public static class Request {
        @NotEmpty
        private String identifier;

        @NotEmpty
        private String password;
    }

}
