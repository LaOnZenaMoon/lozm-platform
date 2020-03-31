package lozm.core.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PutUserDto {

    @Getter
    public static class Request {
        @NotNull
        private Long id;

        private String name;

        private String password;

        private int flag;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
