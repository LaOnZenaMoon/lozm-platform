package lozm.core.vo.sign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lozm.core.code.UserType;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignVo {

    private String name;
    private String identifier;
    private String password;
    private UserType type;


    public SignVo(String name, String identifier, UserType type) {
        this.name = name;
        this.identifier = identifier;
        this.type = type;
    }
}
