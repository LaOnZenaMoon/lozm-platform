package lozm.core.dto;

import lombok.Getter;
import lombok.Setter;

public class PostUserDto {

    @Getter
    public static class Request {
        private String name;

        private String identifier;

        private String password;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
