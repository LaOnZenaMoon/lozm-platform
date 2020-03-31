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
        private Long id;
        private String name;
        private String identifier;
        private UserType type;

        public Response(Long id, String name, String identifier, UserType type) {
            this.id = id;
            this.name = name;
            this.identifier = identifier;
            this.type = type;
        }
    }

}
