package lozm.core.dto;

import lombok.Getter;
import lombok.Setter;


public class GetUserDto {

    @Getter
    public static class Request {

    }

    @Getter
    @Setter
    public static class Response {
        private String name;
        private String identifier;
//        private UserType type;
    }

}
