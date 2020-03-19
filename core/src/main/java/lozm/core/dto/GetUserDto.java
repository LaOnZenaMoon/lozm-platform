package lozm.core.dto;


import lombok.Getter;
import lombok.Setter;

public class GetUserDto {

    @Getter
    public static class Request {
        private String test;
    }

    @Getter
    @Setter
    public static class Response {

    }

}
