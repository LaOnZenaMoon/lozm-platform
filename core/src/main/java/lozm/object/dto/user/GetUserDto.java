package lozm.object.dto.user;

import lombok.*;
import lozm.object.code.AccountType;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetUserDto {

    private Long id;
    private String name;
    private String identifier;
    private String password;
    private AccountType type;


    @Getter
    @Setter
    public static class Response {
        List<GetUserDto> list = new ArrayList<>();
    }

}
