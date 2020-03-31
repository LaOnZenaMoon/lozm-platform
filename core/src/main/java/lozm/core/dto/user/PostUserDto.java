package lozm.core.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

public class PostUserDto {

    @Getter
    public static class Request {
        @NotEmpty
        private String name;

        @NotEmpty
        private String identifier;

        @NotEmpty
        private String password;

        @NotEmpty
        private String type;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
