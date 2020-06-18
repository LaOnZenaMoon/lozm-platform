package lozm.object.vo.sign;

import lombok.*;
import lozm.object.code.UsersType;

import java.io.Serializable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignVo implements Serializable {

    private static final long serialVersionUID = 1000100010000000001L;

    private Long id;
    private String name;
    private String identifier;
    private String password;
    private UsersType type;
    @Setter
    private String token;


    public SignVo(String name, String identifier, UsersType type) {
        this.name = name;
        this.identifier = identifier;
        this.type = type;
    }

    public SignVo(Long id, String name, String identifier, String password, UsersType type) {
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.password = password;
        this.type = type;
    }

}
