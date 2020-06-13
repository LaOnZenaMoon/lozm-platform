package lozm.object.dto.user;

import lombok.Getter;

import javax.validation.constraints.NotNull;

public class PutUserDto {

    @Getter
    public static class Request {
        @NotNull
        private Long id;

        private String name;

        private String identifier;

        private String password;

        private String type;

        private int flag;
    }

}
