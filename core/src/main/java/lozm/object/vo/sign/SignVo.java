package lozm.object.vo.sign;

import lombok.*;
import lozm.object.code.AccountType;
import lozm.object.vo.BaseVo;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class SignVo extends BaseVo implements Serializable {

    private static final long serialVersionUID = 1000100010000000001L;

    private Long id;
    private String name;
    private String identifier;
    private String password;
    private AccountType type;
    @Setter
    private String token;

    @Builder
    public SignVo(LocalDateTime createdDt, LocalDateTime modifiedDt, Long createdBy, Long modifiedBy, int flag, Long id, String name, String identifier, String password, AccountType type, String token) {
        super(createdDt, modifiedDt, createdBy, modifiedBy, flag);
        this.id = id;
        this.name = name;
        this.identifier = identifier;
        this.password = password;
        this.type = type;
        this.token = token;
    }

}
