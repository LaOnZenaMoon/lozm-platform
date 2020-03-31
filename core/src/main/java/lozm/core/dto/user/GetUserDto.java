package lozm.core.dto.user;

import lombok.Getter;
import lombok.Setter;
import lozm.core.code.UserType;


public class GetUserDto {

    @Getter
    public static class Request {

    }

    @Getter
    @Setter
    public static class Response {
        private String name;
        private String identifier;
        private UserType type;

        public Response(String name, String identifier, UserType type) {
            this.name = name;
            this.identifier = identifier;
            this.type = type;
        }
    }

}
