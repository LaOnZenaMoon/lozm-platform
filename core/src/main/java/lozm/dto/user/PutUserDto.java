package lozm.dto.user;

import lombok.Getter;

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

}
