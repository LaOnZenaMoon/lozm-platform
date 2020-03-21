package lozm.core.dto;

import lombok.Getter;
import lombok.Setter;

public class PutUserDto {

    @Getter
    public static class Request {
        private Long id;

        private String name;

        private String password;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
