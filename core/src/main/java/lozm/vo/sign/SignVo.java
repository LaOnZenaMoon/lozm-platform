package lozm.vo.sign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lozm.code.UsersType;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignVo {

    private String name;
    private String identifier;
    private String password;
    private UsersType type;


    public SignVo(String name, String identifier, UsersType type) {
        this.name = name;
        this.identifier = identifier;
        this.type = type;
    }
}
