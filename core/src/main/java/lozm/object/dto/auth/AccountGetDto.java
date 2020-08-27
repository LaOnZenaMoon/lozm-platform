package lozm.object.dto.auth;

import lombok.*;
import lozm.object.code.AccountType;

import java.util.ArrayList;
import java.util.List;

@Getter @Builder
@NoArgsConstructor @AllArgsConstructor
public class AccountGetDto {

    private Long id;
    private String name;
    private String identifier;
    private String password;
    private AccountType type;


    @Getter @Setter
    public static class Response {
        List<AccountGetDto> list = new ArrayList<>();
    }

}
