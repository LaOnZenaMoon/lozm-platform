package lozm.object.dto.user;

import lombok.Getter;
import lombok.Setter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.NotNull;

public class PutUserDto {

    @Getter
    public static class Request extends BaseUserDto {
        @NotNull
        private Long id;

        private String name;

        private String identifier;

        @Setter
        private String password;

        private String type;

        private int flag;
    }

}
