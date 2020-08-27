package lozm.object.dto.auth;

import lombok.Getter;
import lombok.Setter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.NotEmpty;

public class AccountPostDto {

    @Getter
    public static class Request extends BaseUserDto {
        @NotEmpty
        private String name;

        @NotEmpty
        private String identifier;

        @NotEmpty
        @Setter
        private String password;

        @NotEmpty
        private String type;

        public static Request setRequestTestData(String name, String identifier) {
            AccountPostDto.Request reqDto = new AccountPostDto.Request();
            reqDto.name = name;
            reqDto.identifier = identifier;
            reqDto.password = "asdf1234";
            reqDto.type = "USER";

            return reqDto;
        }
    }

}
