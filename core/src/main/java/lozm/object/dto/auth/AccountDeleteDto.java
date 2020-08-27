package lozm.object.dto.auth;

import lombok.Getter;
import lozm.object.dto.BaseUserDto;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
public class AccountDeleteDto {

    private Long id;
    private String name;
    private String identifier;
    private String password;
    private String type;
    private int flag;

    @Getter
    public static class Request extends BaseUserDto {
        @Size(min = 1)
        private List<AccountDeleteDto> list = new ArrayList<>();
    }

}
